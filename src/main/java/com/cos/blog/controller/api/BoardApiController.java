package com.cos.blog.controller.api;

import com.cos.blog.controller.config.auth.PrincipalDetail;
import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.cos.blog.service.BoardService;

import javax.servlet.http.HttpSession;

@RestController
public class BoardApiController {

  @Autowired
  private BoardService boardService;

  @PostMapping("/board")
  public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
    System.out.println("글 작성 호출 완료.");
    boardService.글쓰기(board,principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }

  @DeleteMapping("/api/board/{id}")
  public ResponseDto<Integer> deleteById(@PathVariable int id){
    boardService.글삭제하기(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
}