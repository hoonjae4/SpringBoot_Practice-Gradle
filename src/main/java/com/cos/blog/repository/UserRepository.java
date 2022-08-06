package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//UserRepository는 User table을 관리하는 Repository이다.
// User table의 primary key는 숫자다 라는것이 <User,Integer>
// JpaRepository에는 find, save, delete, select 등등 여러가지 메소드를 제공
// Date Access Object
// 자동으로 bean 으로 등록이 됨. @Repository annatation 생략이 가능

//@Repository

public interface UserRepository extends JpaRepository<User,Integer> {

}

//JPA Naming 전략
// findByUsernameAndPassword 이 함수를 선언만 하면
// SELECT * FROM user WHERE username=? AND password=?, 이 상태의 쿼리가 동작을 하게 됨.
//User findByUsernameAndPassword(String username, String password);

  /* 이런식으로 정의한게 위와 동일하다.
  @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
  User login(String username,String password);
  */

//위 방식의 로그인도 사용 안함. security 사용할것.