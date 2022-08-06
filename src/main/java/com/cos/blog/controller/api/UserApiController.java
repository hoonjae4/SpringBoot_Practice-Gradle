package com.cos.blog.controller.api;

import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("호출 ㅇㅋ");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
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