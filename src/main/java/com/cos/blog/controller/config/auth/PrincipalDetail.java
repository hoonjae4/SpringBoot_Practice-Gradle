package com.cos.blog.controller.config.auth;


import com.cos.blog.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티에서 로그인하고 세션에 등록할때 필요한게 UserDetail type이다.
// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 한다.
@Getter
public class PrincipalDetail implements UserDetails {
  private final User user; //객체를 품고 있는것 -> composition

  public PrincipalDetail(User user){
    this.user = user;
  }
  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  //계정이 만료되지 않았는지 리턴 (true 만ㅇ료되지 않음)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //true로 해야 안잠긴상태
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //비밀번호 만료 여부. true가 만료안됨.
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //게정 활성화가 되어있는지. true가 활성화
  @Override
  public boolean isEnabled() {
    return true;
  }

  //계정의 권한을 리턴하는 함수. 권한이 여러개면 포문 돌려야함.
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> collectors = new ArrayList<>();
    collectors.add(()->{
        return "ROLE_"+user.getRole();
    });

    /*
    위와 같은것. 자바에서는 method를 담을수는 없음. 그렇기에 method를 담기 위해서 객체를 만드는데
    어차피, GrantedAutority는 하나의 함수만을 포함하고 있는 객체이기 때문에
    화살표 함수를 통해 간결하게 표현한것.
    collectors.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "ROLE_"+user.getRole(); //spring에서 role을 받을때의 규칙. 앞에 ROLE_을 꼭 넣어줘야함
      }
     */
    return collectors;
  }
}
