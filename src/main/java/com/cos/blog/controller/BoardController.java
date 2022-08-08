package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    //아무것도 안적힐때와 /가 있을때 다 index.jsp로 간다.
    @GetMapping({"","/"})
    public String index() {
        //yml에 /WEB_INF/views에서 index.jsp로 찾아가게 설정해놨음.
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
