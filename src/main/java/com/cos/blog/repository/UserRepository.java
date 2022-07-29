package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//UserRepository는 User table을 관리하는 Repository이다.
// User table의 primary key는 숫자다 라는것이 <User,Integer>
// JpaRepository에는 find, save, delete, select 등등 여러가지 메소드를 제공
// Date Access Object
// 자동으로 bean 으로 등록이 됨. @Repository annatation 생략이 가능

//@Repository

public interface UserRepository extends JpaRepository<User,Integer> {
}
