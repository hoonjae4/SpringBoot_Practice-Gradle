package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/*
서비스가 필요한 이유
1. 트랜젝션 관리 -> 여러 트렌젝션(save같은 작업)을 하나의 트렌젝션으로 묶어서 서비스화 할 수 있음.
2. 서비스라는 의미 때문
 */

@Service //스프링이 컴포넌트 스캔을 통해 bean에 등록해줌. bean 등록 -> IOC 해주는것. 메모리에 대신 띄워줌.
public class UserService {
  @Autowired //DI, 의존성 주입
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Transactional //회원가입 전체의 서비스가 하나의 transaction으로 묶이게 됨. 성공시 commit, 실패는 rollback
  public void 회원가입(User user) {
    user.setRole(RoleType.USER);
    String rawPassword = user.getPassword();
    String encPassword = encoder.encode(rawPassword);
    user.setPassword(encPassword);
    userRepository.save(user);
  }

  @Transactional
  public void 회원수정(User user){
    User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
      return new IllegalArgumentException("유저 아이디 찾기 실패.");
    });
    if(persistance.getOauth()==null || persistance.getOauth().equals("")){
      String rawPassword = user.getPassword();
      String encPassword = encoder.encode(rawPassword);
      persistance.setPassword(encPassword);
      persistance.setEmail(user.getEmail());
    }

  }

  @Transactional(readOnly = true)
  public User 회원찾기(String username){
    User user = userRepository.findByUsername(username).orElseGet(()->{
      return new User();
    });
    return user;
  }
}




//select만 할거라 transactional이 필요없지만
// select할때 transaction이 시작되고, 서비스 종료시에 transaction 종료 -> 정확성 증가

  /*
  이 로그인 사용 안함
  @Transactional(readOnly = true)
  public User 로그인(User user) {
    return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
  }
   */
