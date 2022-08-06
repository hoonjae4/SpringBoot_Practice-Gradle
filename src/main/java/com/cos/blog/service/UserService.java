package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
서비스가 필요한 이유
1. 트랜젝션 관리 -> 여러 트렌젝션(save같은 작업)을 하나의 트렌젝션으로 묶어서 서비스화 할 수 있음.
2. 서비스라는 의미 때문
 */

@Service //스프링이 컴포넌트 스캔을 통해 bean에 등록해줌. bean 등록 -> IOC 해주는것. 메모리에 대신 띄워줌.
public class UserService {
  @Autowired //DI, 의존성 주입
  private UserRepository userRepository;

  @Transactional //회원가입 전체의 서비스가 하나의 transaction으로 묶이게 됨. 성공시 commit, 실패는 rollback
  public int 회원가입(User user) {
    userRepository.save(user);
    return 1;
  }
}