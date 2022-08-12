package com.cos.blog.controller;

import com.cos.blog.controller.config.auth.PrincipalDetail;
import org.hibernate.annotations.GeneratorType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로를 auth로 제한함 . 즉 인증이 필요없는곳.
// 그냥 주소가 /면 index.jsp로 가도록 허용

@Controller
public class UserController {
    @GetMapping("/auth/joinProc")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){ return "user/loginForm"; }

    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal){
        return "user/updateForm";
    }
}
