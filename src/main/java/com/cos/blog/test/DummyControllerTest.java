package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {
    @Autowired //의존성 주입, DummyController를 메모리에 띄울때 userRepository는 null임
    //autowired가 userRepository를 동시에 메모리에 올려주는 역할을 해줌.
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    //String join(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email)
    //이런식으로 Request Param을 이용해 받아오면 String a, String b, String c로 해도 알아서 매핑됨.
    //여기서는 post 요청시 body에 들어오는 값들을 매핑해 사용할 것이기 때문에 RequestParam 사용 X
    // String username, String password, String email 이런식으로 사용
    // 그러나 또 User 객체를 이용해 생성도 가능
    private String join(User user){


        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "가입완료";
    }
}
