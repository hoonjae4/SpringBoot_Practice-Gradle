package com.cos.blog.controller.api;

import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    /*
    @Autowired
    HttpSession session;
    session을 이렇게 DI 등록을 해서 사용할수 있음.
    */

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("호출 ㅇㅋ");
        //숫자를 그냥 200으로 적는것 보단
        //HttpStatus를 이용해 성공값을 넣어주는게 더 안전
        user.setRole(RoleType.USER); //이 field는 자동으로 등록되게 설정하지 않았으므로 직접 넣음.
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //1이면 성공 -1이면 실패
        //return 1;
        //return 1이면 ajax done의 resp값이 1이다.
    }

    /* 다른 방식으로 로그인 사용할것.
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> longin(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController : login호출됨");
        User principal = userService.로그인(user); //principal(접근주체)

        //세션에 키값을 넣어줘야함. 그래야 front에서 사용 가능.
        if(principal != null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
     */
}
