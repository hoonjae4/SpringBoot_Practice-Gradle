package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//-------롬북 이용 전 ---------
//변수는 다 private으로 만듦
// 변수에 direct로 접근해서 변수를 바로 바꾸는건 객체지향이 아님.
//이 상태값을 바꿀 getter, setter는 우클릭 -> source -> getter,setter
//생성자도 똑같이 generate constructor
//-----------------------
//롬북 이용후에는 @Getter, @Setter 자동완성으로 만들자.
//getter,setter 동시에 만드는건 @Data
@Data

//생성자 만드는 롬북명령어
@AllArgsConstructor

// final 붙은 변수에 대한 생성자 생성
//@RequiredArgsConstructor

//빈 생성자
@NoArgsConstructor

//@Getter
//@Setter

//final -> 데이터가 변경되지 않게 유지. 불변성을 위함.
public class Member {
    private  int id;
    private String username;
    private String password;
    private String email;


}
