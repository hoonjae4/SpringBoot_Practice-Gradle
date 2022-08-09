package com.cos.blog.controller;

import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //아무것도 안적힐때와 /가 있을때 다 index.jsp로 간다.
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
        //yml에 /WEB_INF/views에서 index.jsp로 찾아가게 설정해놨음.
        model.addAttribute("boards",boardService.글목록(pageable));
        return "index"; //viewResolver가 작동해서, 해당 view에 정보가 전송이됨.
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String fintByID(@PathVariable int id,Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }
}
