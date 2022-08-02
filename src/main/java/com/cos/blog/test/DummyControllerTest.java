package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired //의존성 주입, DummyController를 메모리에 띄울때 userRepository는 null임
    //autowired가 userRepository를 동시에 메모리에 올려주는 역할을 해줌.
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //한 페이지당 2건의 데이터를 리턴받아보자.
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }


    @GetMapping("/dummy/user/{id}")
    // {id} 주소로 파라메터를 전달 받을 수 있음
    // pathvariable annoation을 이용해 {id}를 인식하게끔 해주자
    // return type을 User 객체로 할 것이기 때문에 User로 선언
    public User detail(@PathVariable int id) {
        //findById는 optional return이기 때문에 null인지 아닌지 판단을 하기도 해야함.
        //findById.get()은 그냥 바로 user 객체 return -> null이 절대 나올수 없는 상황에서만 사용
        //findById.orElseGet method는 찾아보자.
        // illegalArguementException 으로 잘못된 id값을 처리하자.

        // interface는 new객체를 생성할수가 없음.
        // 그래서 불러오는 동시에 method를 오버라이딩 해서, new 객체를 생성함.
        /*
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override

            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });
        */
        //위 method를 람다식으로 가독성 좋게 만들수 있음

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
        });

        // Restcontroller -> html이 아닌 데이터를 return하는 controller
        // 요청 : 웹브라우저
        // user 객체 -> 자바 오브젝트 -> 웹브라우저가 이해하지 못함
        // 따라서 웹브라우저가 이해할수 있는 데이터로 변환해줘야함 -> JSON -> 스프링의 messageconverter가 응답시 자동으로 작동해서 변환해줌.
        return user;
    }
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
