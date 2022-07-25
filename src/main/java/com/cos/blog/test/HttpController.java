package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//RestController : 사용자가 요청 -> 응답(Data) 해주는것 -> @RestController
// 사용자가 요청했을때 -> 응답(HTML 파일)해주는 Controller -> @Controller
@RestController
public class HttpController {

    private static final String TAG = "HttpController : ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = new Member(1,"asd","asdasd","asdasdasd@asd.asd");
        System.out.println(TAG + "getter : "+ m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter : "+ m.getId());
        return  "lombook test 완료";
    }
    //인터넷 브라우저는 get요청밖에 할 수 없다.
    @GetMapping("/http/get")
    /* http://localhost:8080/http/get  (select)
     http://locallhost:8080/http/get?id=1 (id=1 -> 쿼리스트링)
     @RequestParam int id, @RequestParam String username
     위와같은 작업이 번거로워 Member라는 객체를 생성하고, 여기서 알아서 매핑해줌
     get 방식으로 데이터를 요청하는건 쿼리스트링 밖에 없음. */
    public String getTest(Member m) {
        return "get 요청 : " + m.getId() + " , " + m.getUsername();
    }
    // http://localhost:8080/http/post (insert)
    // post요청은 쿼리스트링으로 주소에 보내는게 아니라 body라는곳에 담아서 보냄.
    // 1. form태그의 input type --> Member클래스로 받아오기 가능
    // 2. text 형태의 요청 -> @RequestBody String text가 필요함.
    // Post 요청은 Get 요청방식과는 다르게, body라는곳에 데이터를 받아오는 것이기 때문에
    // 쿼리스프링의 방식처럼 할수 있는건 form 태그를 이용할때 뿐이다.
    // 3. json 데이터 요청 -> @RequestBody Member m 으로 받으면 JSON 데이터도 처리 가능.
    // 만일 요청 타입이 text/plain인데 application/json 데이터 타입을 요청한다면 오류 발생

    // Json 타입의 요청을 매핑해 주는것 -> MessageConverter(스프링부트)
    @PostMapping("/http/post")
    public String postTest(Member m) {
        return "post 요청 : " + m.getId() + " , " + m.getUsername();
    }
    // http://localhost:8080/http/put (update)
    // put 방식도 post 방식과 동일.
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }
    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
