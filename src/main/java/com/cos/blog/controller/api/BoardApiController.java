package com.cos.blog.controller.api;

import com.cos.blog.controller.config.auth.PrincipalDetail;
import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
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

  @PutMapping("/api/board/{id}")
  public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
    System.out.println("수정하기 컨트롤러 호출.");
    boardService.글수정하기(id,board);
    System.out.println("수정하기 끝남.");
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }

  @PostMapping("/api/board/{boardId}/reply")
  public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){
    System.out.println("댓글 작성 호출 완료.");
    boardService.댓글쓰기(principal.getUser(),boardId,reply);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
}