package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// @DynamicInsert // null인 부분을 자동으로 insert하지 않음.
@Entity // User 클래스가 자동으로 MySQL에 테이블이 생성 된다.
public class User {

    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 database의 넘버링 전략을 따라간다.
    private int id; //시퀀스, mysql에서는 auto_increment

    //각 변수 바로 위에 annotaion을 주어서 제약 넣어주는것.
    @Column(nullable = false,length = 100, unique = true)
    private String username;
    //암호화 시킬것이기 때문에 길이 넉넉하게
    @Column(nullable = false,length = 100)
    private String password;
    @Column(nullable = false,length = 50)
    private String email;
    @ColumnDefault("'user'") // 문자라는걸 알려주기 위해 따옴표 넣어주고. role의 디폴트값 정해줌.
    //private String role; //String말고 Enum으로 사용하는게 더 좋음. admin,user,manager의 권한을 넣는것인데 String이면 말도안되는 role을 넣어버릴수도 있음.
    @Enumerated(EnumType.STRING) //이 field의 enumtype은 string이다.
    private RoleType role;

    private String oauth; //kakao,google.


    @CreationTimestamp // 시간이 자동으로 등록됨. 물론 MySQL에서 now나 자바에서 systime을 이용해도 상관없음.
    private Timestamp createDate; //자바 sql이 내재한 변수


}
