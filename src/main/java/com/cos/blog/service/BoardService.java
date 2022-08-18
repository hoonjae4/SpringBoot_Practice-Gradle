package com.cos.blog.service;

import com.cos.blog.controller.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
  @Autowired
  private BoardRepository boardRepository;

  @Transactional
  public int 글쓰기(Board board, User user) { //title,content
    board.setCount(0);
    board.setUser(user);
    boardRepository.save(board);
    return 1;
  }

  @Transactional(readOnly = true)
  public Page<Board> 글목록(Pageable pageable){
    return boardRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Board 글상세보기(int id){
    return boardRepository.findById(id).orElseThrow(()->{
      return new IllegalArgumentException("글 상세보기 : 아이디 찾기 실패.");
    });
  }

  @Transactional
  public void 글삭제하기(int id){
    boardRepository.deleteById(id);
  }

  @Transactional
  public void 글수정하기(int id,Board requestBoard){
    Board board = boardRepository.findById(id).orElseThrow(()->{
      return new IllegalArgumentException("글 찾기 실패 : 아이디 찾을수 없음.");
    });
    board.setTitle(requestBoard.getTitle());
    board.setContent(requestBoard.getContent());
    System.out.println("수정하기 service 호출됨");
    //해당 함수 종료시 트랜잭션이 종료되고, 영속화 되어잇는 board의 데이터가 달라졌기 때문에 자동으로 update됨. 따로 save 안해도 된단것.
  }

  @Autowired
  private ReplyRepository replyRepository;
  @Autowired
  private UserRepository userRepository;
  @Transactional
  public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto){
    User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
      System.out.println(replySaveRequestDto.getUserId());
      return new IllegalArgumentException("댓글 쓰기 실패 : 사용자를 찾을 수 없습니다.");
    });
    Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
      return new IllegalArgumentException("댓글 쓰기 실패 : 게시글을 찾을 수 없습니다.");
    });

    Reply reply = Reply.builder()
            .user(user)
            .board(board)
            .content(replySaveRequestDto.getContent())
            .build();
    replyRepository.save(reply);
  }
}

