package com.cos.blog.controller.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Bean등록
public class PrincipalDetailService implements UserDetailsService {

  @Autowired
  public UserRepository userRepository;

  //스프링이 로그인을 가로챌때 username과 password라는 변수 두 개를 가로채는데,
  // password 가 틀린 부분 처리는 알아서 하기 때문에 해당 username이 db에 있는지만 확인해서 리턴 해주면 된다.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User principal = userRepository.findByUsername(username)
            .orElseThrow(()->{
              return new UsernameNotFoundException("해당 사용자를 찾을수 없음 : " +username);
            });
    return new PrincipalDetail(principal); //이때 시큐리티 세션에 유저 정보가 저장이 됨.
  }
}
