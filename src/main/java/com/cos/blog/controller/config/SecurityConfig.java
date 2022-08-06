package com.cos.blog.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Bean 등록이랑 스프링 컨테이너에서 객체를 관리할수 있게 하는것

@Configuration
@EnableWebSecurity // 원래는 모든 요청은 Controller에서 관리하는데, 이 어노테이션을 걸면 필터를 작동하게함.
// 시큐리티를 거쳐서, 인증이 없이 이동할 수 있는곳은 그냥 가고, 인증이 필요한 곳은 인증을 하도록 함. 그 설정이 http 이하 코드
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소에 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //이 함수를 통해 비밀번호 암호화.
  @Bean //IOC가 됨. 필요할때마다 가져와서 쓰면 되는것.
  public BCryptPasswordEncoder encodePWD(){
    return new BCryptPasswordEncoder();
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable() //csrf 토큰 비활성화 테스트시 넣어주자.
            .authorizeRequests()
              .antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //auth는 아무나 들어올수 있다. any matchers
              .permitAll()
              .anyRequest() //이외의 다르 모든 요청은
              .authenticated()//인증이 되어야함.
            .and()
              .formLogin()
              .loginPage("/auth/loginForm"); // 인증이 필요한 곳이 있다면 loginForm으로 이동하라
  }
}
