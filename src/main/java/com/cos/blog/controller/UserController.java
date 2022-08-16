package com.cos.blog.controller;

import com.cos.blog.controller.config.auth.PrincipalDetail;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//인증이 안된 사용자들이 출입할 수 있는 경로를 auth로 제한함 . 즉 인증이 필요없는곳.
// 그냥 주소가 /면 index.jsp로 가도록 허용

@Controller
public class UserController {

    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    private UserService userService;
    @GetMapping("/auth/joinProc")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){ return "user/loginForm"; }

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }



    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){
        //@ResponseBody -> 데이터 리턴 함수
        //POST 방식으로 key value 타입의 데이터를 요청
        RestTemplate rt = new RestTemplate(); //http 요청을 간단하게 할수있는것.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        //전송할 데이터가 key,value임을 알려줌.

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","8486dbad96748f7d1e942c5a6ac3c7b2");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);

        //httpheader와 httpbody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        // Http 요청하기 = post방식 - 그리고 response 변수의 응답을 받음.
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // Gson, Json simple, ObjectMapper 라이브러리 중에 ObjectMapper사용.
        // json 데이터를 java 객체로 변환하는 작업. object mapper를 통해 response 데이터를 OAuthToken으로 변환해주자.
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken= null;
        try {
            oauthToken = objectMapper.readValue((String)response.getBody(),OAuthToken.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        //System.out.println(oauthToken.getAccess_token());

        //Access_token으로 사용자 정보 가져오기.
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        ResponseEntity response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        //Objcet Mapper로 response2 객체를 자바 객체로 변경.
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue((String)response2.getBody(),KakaoProfile.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 사용자(이름) : " + kakaoProfile.getProperties().getNickname());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        // 이 정보로 username, password, email을 구성할 것.
        System.out.println("블로그 서버 유저 네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("블로그 서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(cosKey)
                .oauth("kakao")
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();

        User originUser = userService.회원찾기(kakaoUser.getUsername());
        if(originUser.getUsername() == null) {
            System.out.println("신규회원. 카카오 회원가입");
            userService.회원가입(kakaoUser);
        }
        //만일 카카오 유저가 있으면 세션에 등록해줌.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}
