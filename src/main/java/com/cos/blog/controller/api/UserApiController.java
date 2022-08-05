package com.cos.blog.controller.api;

import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("호출 ㅇㅋ");
        //숫자를 그냥 200으로 적는것 보단
        //HttpStatus를 이용해 성공값을 넣어주는게 더 안전
        return new ResponseDto<Integer>(HttpStatus.OK,1);
        //return 1;
        //return 1이면 ajax done의 resp값이 1이다.
    }
}
