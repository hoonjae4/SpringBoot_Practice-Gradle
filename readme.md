# 스프링 연습

---

## 1강 준비물

1. Oracle JDK 1.8 설치

> https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

---

2. MySQL 설치

> https://dev.mysql.com/downloads/windows/installer/5.7.html

>> 알아서 최신버전으로 설치하자.
>>

---

3. 폰트 설치

> https://www.cookierunfont.com/#section7

>> TTF regular
>>

---

4. STS 툴 설치

> # https://spring.io/tools

>> + 현재 VsCode로 설치해서 학습중.
>> + Eclipse로 바꿈..
>>

---

5. 인텔리 J - 키맵 세팅

   Eclipse Marketplace - IntelliJ IDEA keymap for Eclipse 0.1
6. UTF-8 세팅
7. 폰트 세팅

---

## 2강

### UTF - 8 쓰는 이유

입력 신호 -> 1 or 0
2^1 - > 2가지
2^2 - > 4가지
.
.
.
2^8 -> 256가지

	*2^8 = 8 bit = 1 byte
	
	*만일 1byte로 문자를 표현한다 -> 한글(1byte), 중국어(3byte)와 같은 문자 깨짐.
	*그래서 3byte를 전세계 표준으로 이용하고, 이를 UTF-8이라 부름.

---

## 3강

### 의존성 패치

1. Spring Boot DevTools

> https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html

다양한 개발 편의를 제공해줌.
특히 Automatic restart 제공. -> 자동 재시작. 파일 변경시 자동으로 재시작 해주는기능.

2. Lombok

> https://projectlombok.org/

getter, setter같은것을 자동으로 생성해줌.

3. Spring Data JPA

DB를 JPA를 이용해 다룰 것이라 설치.

4. MySQL Driver
5. Spring Security

보안적인 기능 제공.

6. OAuth2 Client - 세팅안함.

> 직접 노가다로 카카오 로그인 구현하여 개념잡기
> 다음 인스타 프로젝트에서는 사용할 것임.
> JWT는 APP과 연동할 때 사용할 예정

7. 템플릿 엔진

> jsp 사용할 예정

8. Spring Web

> WEB MVC를 사용하여 웹 애플리케이션을 만드는데 필요한 스프링부트의 기본적인 요소를 가짐.
> 내장형 컨테이너로 톰캣을 기본탑재하고 있다.

10. 추가 적인 것들

> https://mvnrepository.com/

<!-- 시큐리티 태그 라이브러리 -->
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
<groupId>org.apache.tomcat.embed</groupId>
<artifactId>tomcat-embed-jasper</artifactId>
</dependency>

<!-- JSTL -->
<dependency>
<groupId>javax.servlet</groupId>
<artifactId>jstl</artifactId>
</dependency>
위 코드를 porm.xml로 가서 dependencies에 넣어줌.

* jstl은 템플릿 개발을 편하게 해주는 태그, JSP에서 사용
* tomcat-jasper : 스프링은 JSP를 기본적으로 지원하지 않음. 그래서 따로 폴더 설정을 해줘야 하는데, 그때 jasper가 인식해줌.
* security 관련해서는 전부 태그처리 하고, 나중에 사용.
* mysql또한 태그처리.

---

## 4강. 프로젝트 실행해보기

### BlogControllerTest를 만들때 com.cos.X 라는 아무 패키지나 만들어서 넣으면 안됨.

* 스프링 -> IoC -> 제어의 역전 -> 너(you)가 new해서 띄우지마. 내(스프링)가 new해서 메모리에 띄울게
  * 레퍼런스 변수를 스프링이 관리 해주겠다는 것.
    * method 내부(stack)에서 만든 객체는 지역변수에서 관리하기 때문에 다른 클래스에서 사용할수가 없음.
    * 그래서 이러한 변수들을 따로 사용하기 위해서는 new,new,new하면서 새로운 객체를 생성하는 형태를 계속 띄게 되는데, 이러면 낭비되는 자원들이 많이 생기고 복잡함
    * 그래서 스프링에서 직접 이걸 관리해주는게 제어의 역전.
    * 스프링이 초반에 Component scan을 해서 필요한 것들을 메모리에 로드함. (IoC라고함)
    * 이 공간을 스프링 컨테이너라고 하고, 이 프로젝트에서 그 공간이 com.cos.blog임. 그렇기에 com.cos.X라는 패키지를 만들어서 클래스를 만들어 버리면, 작동하지 않음. 그래서 항상 com.cos.패키지명 이하에서 우리는 항상 작업을 할 것.

    ### IoC 관련해서는 꼭 구글링해서 찾아볼 것.

---

## 5강. MySQL 환경세팅

1. 사용자 생성 및 궈한 주기 및 DB 생성

   -- 유저이름@아이피주소
   create user 'cos'@'%' identified by '자기가 설정할 비밀번호';
   -- ON DB이름.테이블명
   -- TO 유저이름@아이피주소

   -- 해당 유저에게 모든 권한을 줌.
   GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';

   CREATE DATABASE blog CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
   use blog;

만일 cos라는 user가 존재한다면
drop user cos;
를 통해 삭제하고, 각 줄마다 ctrl + enter를 이용해 한줄씩 실행해주자.

2. MySQL 한글 설정

   C -> ProgramData -> MysqlServer -> my.ini파일을 수정

   [client]
   default-character-set=utf8

   [mysql]
   default-character-set=utf8

   [mysqld]
   collation-server = utf8_unicode_ci
   init-connect='SET NAMES utf8'
   init_connect='SET collation_connection = utf8_general_ci'
   character-set-server=utf8
   각 위치에 맞는 곳을 찾아 넣어주자.
   그리고 MySQL 재시작.
3. MySQL 세팅이 완료되었으니 porm.xml에서 mysql 관련 주석처리 해제, jpa 해제.

---

## 6강. MySQL 스프링 연결

src/main/resources/application.yml 에 아래 소스코드 넣기

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul
    username: cos
    password: 자기가 설정한 비밀번호.
yml은 json 파일 설정으로, 모든 spring에 대한 설정을 할 수 있음.
예전에는 xml파일에 설정을 했는데 spring은 yml에 설정함.

property보다 yml이 더 편해서 yml이용함.
property는 spring.datasource~~ 이런식으로 저장한다면
yml은
spring:
database:
이런식으로 관련된 속성들로 묶어놓기 떄문에 좀 더 가시성이 좋다.

#### 여기서 나는 porm.xml에서 maven plug in 오류가 발생해. db를 정상적으로 연결할수 없는 상태에 빠졌다.

#### 이를 해결하기 위해 의존성 패치를 추가로 해주었다
```text
<dependency>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>2.4.3</version>
</dependency>
```
#### 그리고 프로젝트 우클릭 -> run as -> maven install

#### 이클립스 새로고침(F5)

#### 프로젝트 우클릭 -> maven -> update project를 해주니 해결되었다.

---

## 7강 git 연동 -> 스킵.

---

## 8강 git의 추상적 개념

git -> 프로젝트 관리

---

## 9강 git의 3가지 영역

Ex) 회원가입 기능 - Join.java, 로그인 기능 - Login.java

1. Working directory

> 현재 작업하는 폴더. Join.java를 작업하던 곳.
> Join.java 파일에 대한 기능이 어느정도 완성이 되면. 이 Join.java에 대한 Snapshot을 찍고 Index 폴더로 옮김 (add 명령어)

2. Index

> 앞의 add 명령어로 Working directory와 동기화가 되었지만, Head와는 동기화가 되지 않음.
> 이 Join.java라는 작업을 영구히 보관하고 싶어, Commit 이라는 명령어로 Tree에 보관함.

3. Head

> Commit으로 3 영역이 동기화가 됨.
> Head(트리형태)에는 커서가 존재하는데, commit이 될때마다 그 커서가 최근에 Commit된 커서로 이동함.
> Working directory와 Head의 비교는 이 커서로 비교함.
> 만일 Login.java 파일을 없에고 싶다면 커서를 옮겨 Join.java 파일로 옮기면 됨.

#### Git은 이 3 영역을 일치 시키는것(동기화)

> #### 프로젝트의 관리 -> 이 3가지 영역을 동기화 시키는 작업

---

## 10강 Http1.1 설명 -> 스킵

---

## 11강 stateless란

> stateless방식은 한번의 요청과 응답이 이루어지면 클라이언트와 서버와의 연결선이 끊어지는 것을 말한다. 이 방식은 주로 http에 사용되는 방식이며 이는 서버의 부하를 줄여준다.

---

## 12강 MIME 타입 -> 스킵 개별공부

---

## 13,14강 http 요청 실습

RestController -> 응답을 data로
Controller -> 응답을 html로

#### 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다

* post, put, delete를 실습했을때 405오류 발생.

#### 객체 만들때 항상 private로 만들것 -> 객체지향 특징 이용

* getter와 setter 그리고 constructor를 이용해 변수를 다루자.

#### Controller에서 각 요청마다 데이터를 다루는 법이 다름

* ##### GET 요청

  * 인터넷 브라우저에서는 쿼리스트링 즉 주소 뒤에 ?id=1&username=asd 이런식의 요청밖에 다룰수 없으며, Controller 매개변수에 클래스 혹은 String parameter로 받아오면 된다. 다만, String으로 받아오는 경우에는 @RequestParam String Text 형태의 매개변수를 쿼리스트링으로 받아오는 갯수만큼 선언해줘야 하기 때문에 코드가 깔끔하지 못하다. 그렇기에 이번 예제에서는 Member라는 클래스를 생성해 깔끔하게 받아옴

  ```java
   public String getTest(Member m) {
      return "get 요청 : " + m.getId() + " , " + m.getUsername();
  }
  ```

###### POST요청 (PUT도 동일)

* POST요청은 데이터를 쿼리스트링이 아닌 body라는 곳에 담아서 보낸다.
* 이 방식은 3가지 정도로 나뉠수 있다 (더 많음)
* 1. form 태그로 받아오는 데이터
     ```java
     public String postTest(Member m) {
         return "post 요청 : " + m.getId() + " , " + m.getUsername();
     }
     ```

  * 앞서 쿼리스트링에서 클래스를 이용해 데이터를 받아오는것과 동일하게 다루면 된다.
* 2. Text 타입 -> Text를 있는 그대로 출력

```java
public String postTest(@RequestBody String text) {
    return "post 요청 : " + text;
}
```
* 3. JSON 타입 -> 스프링의 MessageConverter가 자동으로 클래스에 선언된 변수에 맞게 매핑해준다

```java
public String postTest(@RequestBody Member m) {
    return "post 요청 : " + m.getId() + " , " + m.getUsername();
}
```
---

## 15강 - Maven에 대해

프로젝트를 진행하는데 필요한 라이브러리가 ojdbc라고 한다면
프로젝트 진행 순서

1. 오라클 사이트에서 ojdbc 다운받음
2. 프로젝트 폴더에 복사(copy)함
3. 프로젝트가 인식할수 있게 빌드함

프로젝트가 하나 더 있고 그 프로젝트에서도 ojdbc가 필요하다면 위와 같은 과정이 또 필요함 결국 ojdbc가 양쪽 2개가 들어감

우리는 이렇게 하지 않고 C\lib라는 폴더에 ojdbc를 두고 사용하면 더 편하지 않을까? 필요한 것을 외부에 두면 편하지 않을까?

만일 배포를 한다고 가정했을때도, 리눅스 서버에는 ojdbc가 없기 떄문에 /home/cos/lib라는 폴더를 만들어 ojdbc를 직접 삽입하는 과정을 거쳐야 하는데, 이게 굉장히 귀찮고 복잡함.

##### 그렇기 때문에 중앙 저장소를 하나 만들고 여기에 여러 라이브러리에 대한 정보를 넣어놓는것(다운로드 사이트). 그리고 우리는 중앙 저장소에만 접근을 해서 라이브러리를 다운 받는 것. -> porm.xml에 저장된것, 필요한 라이브러리에 대한 정보 기술만 해놓음.

##### 메이븐의 원리가 이것임.

배포를 할 때도 porm.xml 덕분에 라이브러리 다운이 쉬워짐.

---

## 16강 Lombok 세팅

C\사용자\사용자명\.m2\repository -> maven이 관리하는 라이브러리(의존성)들

repository\org\projectlombook\lombook\버전 -> jar파일 cmd로 실행(git bash도 가능)후 idle을 sts로 설정후 닫기.

**롬북을 이용하면 @Getter,@Setter를 이용해 getter,setter를 쉽게 생성 가능하고, 둘 다 생성하고 싶으면 @Data를 이용하자.**
**@AllArgsConstructor -> 생성자**

```java
Member m = new Member(1,"asd","asdasd","asdasdasd@asd.asd");
Member m = Member.builder().username("ssar").password("asdasd").email("aaa@naaa.aaa").build();
System.out.println(TAG + "getter : "+ m.getId());
m.setId(5000);
System.out.println(TAG + "setter : "+ m.getId());
```
**@builder를 이용하면 객체 생성시 원하는 필드를 직접 입력해 가져올수 있음.**

Member.java

```java
@Builder
public Member(String username, String password, String email) {
this.username = username;
this.password = password;
this.email = email;
}
```
**BlogApplication.java**

```java
Member m = Member.builder().username("ssar").password("asdasd").email("aaa@naaa.aaa").build();
```
---

## 17강. yml 설정

프로젝트 설정 파일을 말하는데 종류에는 xml,json,yml,yaml등이 있다
xml은 가독성이 불편하지만 이를 개선한게 json이고 json보다 더 개선된게 yaml이다.
yaml에서 key,value 사이에는 한칸씩 띄워져 있어야함 (space)

`server: port: 8000 servlet: context-path: /blog encoding: charset: UTF-8 enabled: true force: true`

`spring: mvc: view: prefix: /WEB-INF/views/ suffix: .jsp`

`datasource: driver-class-name: com.mysql.cj.jdbc.Driver url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul username: cos password: cos1234`

`jpa: open-in-view: true hibernate: ddl-auto: create naming: physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl use-new-id-generator-mappings: false show-sql: true properties: hibernate.format_sql: true`

`jackson: serialization: fail-on-empty-beans: false`

우리 프로젝트에서 사용하는 yml

* server

  * port -> 내가 사용할 포트
  * context-path -> localhost:8080 다음 나올 주소.
* database

  * db 진입을 위한 기본적인 설정

브라우저는 정적 파일을 요청해야 하는데, jsp는 정적 파일이 아님.
그래서 브라우저에서 찾지를 못함. 그래서 src -> main -> webapp->WEB-INF-views 라는 폴더로 옮겨서
여기서 인식을 시켜줘야함.

여기서 jsp의 경로를 인식시켜 주기 위해 yml에 설정을 주입시켜줌.

* spring

  * prefix : controller가 return할때 앞에 붙여주는 경로명
  * suffix : 뒤에 붙여주는 경로명
  * 즉 test가 리턴값이면 /WEB-INF/views/test.jsp/test.jsp.jsp
* jpa

  * use-new-id-generator-mappings : false -> jpa가 사용하는 기본 넘버링 전략을 사용하지 않는다
  * ddl-auto: create -> 프로젝트 실행할때마다 db를 자동으로 만드는것. 나중에는 update로 바꿔줘야함.
  * show-sql : 콘솔창에 db 상태를 보여줄 것인가.
  * properties.hibernate.format_sql : true -> show-sql 동작시 원래 한줄로 나오는데, 이를 보기 편하게 바꿔줌
  * physical-strategy -> entity(table)을 만들때, 변수명 그대로 db에 field를 넣어준다는 것.
    * 만일 SpringPhysicalNamingStrategy를 사용하면 만일 myEmail이라는 필드가 myEmail이 아니라, my_Email 이런식으로 저장함.

나머지는 진도나가면서 차근차근 알아갈 예정.

---

## 18. user 테이블 생성

ORM -> JAVA(다른언어) Object -> 테이블로 매핑해주는 기술 (JPA의 기술)

```java
package com.cos.blog.model;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity // User 클래스가 자동으로 MySQL에 테이블이 생성 된다.
public class User {

    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 database의 넘버링 전략을 따라간다.
    private int id; //시퀀스, mysql에서는 auto_increment

    //각 변수 바로 위에 annotaion을 주어서 제약 넣어주는것.
    @Column(nullable = false,length = 30)
    private String username;
    //암호화 시킬것이기 때문에 길이 넉넉하게
    @Column(nullable = false,length = 100)
    private String password;
    @Column(nullable = false,length = 50)
    private String email;
    @ColumnDefault("'user'") // 문자라는걸 알려주기 위해 따옴표 넣어주고. role의 디폴트값 정해줌.
    private String role; //String말고 Enum으로 사용하는게 더 좋음. admin,user,manager의 권한을 넣는것인데 String이면 말도안되는 role을 넣어버릴수도 있음.
    @CreationTimestamp // 시간이 자동으로 등록됨. 물론 MySQL에서 now나 자바에서 systime을 이용해도 상관없음.
    private Timestamp createDate; //자바 sql이 내재한 변수

}
```
---

## 19 데이터베이스 한글설정 -스킵

---

## 20~21. Board,Reply 테이블 생성 -> User와 동일하게 설정하자

**한 User가 여러개의 Board를 작성할 수 있으므로 Board의 테이블을 설정할때 User를 Foreign key로 갖도록 설정하자.**
**Reply도 Board와 Many to one, User와 Many to one 을 형성하도롱 설정하자**

**Board.java**

```java
@ManyToOne // board가 many, user는 one ->한 명의 유저는 여러 글을 쓸 수 있다.
@JoinColumn(name="userId")
private User user; //글을 적은 사람. DB는 오브젝트를 저장할수 없기때문에 foreign key를 사용하지만, java에서는 오브젝트를 저장할수 있음.
// 그러나 이러면 db에서 충돌이 나지 않는가? -> JPA ORM을 사용하면 이를 자동으로 해결해줌. 자동으로 foreignkey로 인식
```
**Reply.java**

```java
    @ManyToOne
    @JoinColumn(name="boardId")
    private Board board;
  
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
```
---

## 22강 - 연관관계의 주인

매우 어려우니 따로 학습하자.

연관관계의 주인을 설정하는 이유와
fetch 속성에 대해서 따로 학습하기
---------------------------------

## 23강 - JSON 사용법 - 스킵

---

## 24강 - 회원가입을 위한 insert 테스트

연관관계에서 ManyToMany는 잘 사용하지 않음
중간 테이블을 만들어서 OTM OTM으로 활용함

JpaRepository interface를 이용하면 Table을 손쉽게 다룰수 있다.

Repository 사용시 사용하고자 하는 Controller에 @Autowired annotation 작성해서 의존성 주입 하자.

---

## 25강 - 회원가입을 위한 enum 사용법

**user model에 @DynamicInsert를 추가해, null인 필드를 자동으로 insert 하지 못하게 함**
앞 예제에서 role에 default를 이미 user로 설정한 채로 insert를 하는데, 필드를 지정해주다 보니 null값이 들어가 default인 user가 들어가지 않는 현상이 생김.
이런 현상을 방지하기 위해 null값은 insert를 하지 않게끔 dynamicinsert를 넣어주자.

Annotation에 의존하는건 좋지 않음.
그래서 직접 수정하기로 함.
아래의 예제와는 다르게 if문을 이용해서 null이면 default값으로 빼주는 형태로 만들수도 있음.

**DummyController.java**

```java
user.setRole(RoleType.USER);
```
**User.java**

```java
@Enumerated(EnumType.STRING) //이 field의 enumtype은 string이다.
private RoleType role;
```
**RoleType.java**

```java
package com.cos.blog.model;

public enum RoleType{
    USER, ADMIN,
}
```
이런식으로 enum을 이용하면 만일 개발자가 field를 집어넣을때 헷갈리거나 실수할 일이 적어진다. 그냥 String으로 해도 상관은 없다.

## 25 + a - yml, jpa-ddl 을 create에서 update로 변경

---

## 26 - id로 select

```java
public User detail(@PathVariable int id) {
//findById는 optional return이기 때문에 null인지 아닌지 판단을 하기도 해야함.
//findById.get()은 그냥 바로 user 객체 return -> null이 절대 나올수 없는 상황에서만 사용
//findById.orElseGet method는 찾아보자.
// illegalArguementException 으로 잘못된 id값을 처리하자.

        // interface는 new객체를 생성할수가 없음.
        // 그래서 불러오는 동시에 method를 오버라이딩 해서, new 객체를 생성함.
        /*
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override

            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });
        */
        //위 method를 람다식으로 가독성 좋게 만들수 있음

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
        });

        // Restcontroller -> html이 아닌 데이터를 return하는 controller
        // 요청 : 웹브라우저
        // user 객체 -> 자바 오브젝트 -> 웹브라우저가 이해하지 못함
        // 따라서 웹브라우저가 이해할수 있는 데이터로 변환해줘야함 -> JSON -> 스프링의 messageconverter가 응답시 자동으로 작동해서 변환해줌.
        return user;
    }
```
---

## 27 - 전체 select 및 paging 테스트

스프링부트에는 다양한 annotation이 있으며 이를 통해 개발을 편리하게 할 수 있다.
이를 이용해 paging을 테스트 해보자

먼저 전체 select는 간단하니 코드만 보고 가자

```java
@GetMapping("/dummy/user")
    public List<User> list(){
        return userRepository.findAll();
    }
```
간단한 소스코드이다. repository를 이용해 findall을 해주면 전체 select가 된다

페이징은 annotation을 이용해서 진행한다

```java
  @GetMapping("/dummy/user/page{id}")
  public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
      Page<User> users = userRepository.findAll(pageable);
      return users;
  }
```
먼저 return을 Page 객체로 잡고, @PageableDefault annotation을 이용한다.
size는 한 페이지에 몇개의 user를 가져올 것인가, sort는 정렬 기준, direction는 오름차순과 내림차순을 정하는 방식이다.
그래서 localhost:8080/blog/dummy/user/page?page=0,page=1,page=2 이런식으로 paging이 가능하다.
여기서 page=0은 Pageable pageable 에서 받아오는 **쿼리스트링**이다.

그러나, return값이 Page객체이기때문에 필요한 content 영역 외의 값인 page객체의 요소들이 추가로 출력이 될 수 있다. 이를 예방하기 위해 return값을 List로 바꾸고
getcontent method를 이용해 user content만 return을 해줄수 있다

```java
  @GetMapping("/dummy/user")
  public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
      List<User> users = userRepository.findAll(pageable).getContent();
      return users;
  }
```
```java
  @GetMapping("/dummy/user")
  public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
      Page<User> pagingUser = userRepository.findAll(pageable);
    
      List<User> users = pagingUser.getContent();
      return users;
  }
```
그러나, Page 객체의 필드값을 이용하는 경우도 있으니, Page객체를 따로 선언하고 필요한 부분만을 return하며 사용할수도 있음.

---

## 28강 - update test

Update시에는 @PutMapping을 이용함.
Put형식과 Get형식은 스프링에서 알아서 구분해주기 때문에 주소가 같아도 ㄱㅊ.
Update시에는 Repository의 save method를 이용함.
save는 insert에 이용하는데, 만일 DB에 이미 존재하는 id가 있다면 update를 해줌.
그러나, 지정하지 않은 다른 field의 값은 null로 바뀌는 현상이 생기는데, 이를 예방하기 위해 put요청시 받아온 id값을 통해 객체를 찾고, 필요한 부분만 수정해 save를 해주는 형태로 사용함

```java
@PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        requestUser.setId(id);
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        userRepository.save(requestUser);
        return null;
    }
```
@Transactional annotation을 통해 save가 없이도 데이터가 update되게끔 만들수 있는데 이를 '더티체킹' 이라고함.
**DummyControllerTest.java**

```java
@Transactional
@PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        requestUser.setId(id);
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        return null;
    }
```
---

## 29강 - 더티체킹, 영속성검사

더티체킹 - 상태 변경 검사

JPA에서는 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스에 반영한다.
그렇기 때문에 값을 변경한 뒤, save를 하지 않더라도 DB에 반영이 된다.

원리

* 영속성 컨텍스트란 서버와 DB사이에 존재함.
* JPA는 엔티티를 영속성 컨텍스트에 보관할 떄, 최초 상태를 복사해서 저장해둠. (일종의 스냅샷)
* 트랜잭션이 끝나고 flush할 때 스냅샷과 현재 엔티티를 비교해 변경된 엔티티를 찾아냄.
* JPA는 변경된 엔티티를 DB단에 반영하여 한번에 쿼리문을 날림.

---

## 30강 - delete 연습

Repository의 deleteById를 이용해 데이터를 삭제할때, 그냥 삭제 해버리면 존재하지 않거나, 잘못 매칭된 값을 삭제하는 경우가 있으니
try catch를 통해 분기를 해주도록 하자.

```java
  @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        } //catch에서 execption은 오류뜨는거 보고 확인해서 잡으면 되는데
        // 귀찮으면 그냥 Exception e로 잡아도 됨.
        catch (EmptyResultDataAccessException e){
            return "삭제 실패 - 존재하지 않는 ID";
        }
        return "삭제완료";
    }
```
---

## 31강 - Exception 처리

Exception을 처리할때 그냥 IllegalArgumentException과 같은 화면을 그냥 다 출력하는건 크게 의미가 없다.
그래서 우리는 Exception handler를 따로 만들어서 handling 해주면 된다.

```java
package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

    @ControllerAdvice //모든 Exception이 발생하면 이 Class로 들어오게 하는 Annotation
    @RestController
    public class GlobalExceptionHandler {
        //IllegalArgumentException에 대한 Exception이 발생하면 이곳으로 오라.
        //만일 모든 Exception을 하나로 처리하고 싶으면 Exception.class로 하면 된다.
        @ExceptionHandler(value = IllegalArgumentException.class)
        public String handleArgumentException(IllegalArgumentException e) {
            return "<h1>"+e.getMessage()+"</h1>";
        }
}
```
프로젝트에 handler라는 패키지를 따로 만들어 GlobalExceptionHandler.java 클래스를 생성해주고
@ControllerAdvice Annotation을 통해 모든 Exception 발생시, 이 Class를 호출하게 해준다.
그리고 @ExceptionHandler(value=?) 를 통해서 Handle하고자 하는 Exception을 설정해주면된다.

---

## 32강 - 스프링 기본 파싱 전략과 Json 통신

1. Get요청
   주소에 데이터를 담아 보낸다. key=value
   localhost:8080/blog/user?**username=ssar**
   특징 : 데이터를 body로 보내지 않음
2. Post,Put,Delete
   보통 담아 보내야할 데이터가 많음.
   그래서 Body에 데이터를 담아 보낸다. -> form 태그 post -> 자바스크립트로 요청해야함.
   이 때 데이터의 형태는 Json이 좋다. (Ajax요청 and 데이터는 Json으로 통일)
3. 스프링 컨트롤러의 파싱 전략
   스프링 컨트롤러는 key=value를 자동으로 변수에 담아줌.
   그래서 key=value형태는 변수 Controller(String username, String email)등으로 받을수 있음
   이 때 해당 object에 setter가 없으면 불가능.
   ```java
   public String home(User user) {
        return "home";
   } 
   ```
   의 형태에서 user에 setter가 없으면 불가능.
   우리가 작성했던 DummyController Join 함수에서도 이와 같은 형태를 사용해서 body에 담긴 key value 매칭을 자동으로 user에 해줌.
   만일 없는 필드의 key,value값은 스프링에서 자동으로 무시해버림.
4. **key-value가 아닌 Data는 @RequestBody 매개변수에 Annotation을 걸어준다.**
5. Json Data를 Ajax로 처리하자.

---

## 33강 - 기본화면 구성 - 간단한 부트스트랩 작업이므로 패스.

## 34강 - 로그인. 회원가입 구성

HTML 에서 자바스크립트 Script태그는 최하단에 두자. 그래야 인터프리터가 읽으면서 식별 가능.

## 35강 - 회원가입 기능

static/js 에 정적인 자바스크립트 소스파일을 두자.

## 36 37 생략

---

## 38강 - Ajax요청 쏘기

**controller/Dto/ResponseDto.java**

```java
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class ResponseDto<T> {
        HttpStatus status;
        T data;
    }
```
**controller/apt/UserApiController.java**

```java
    package com.cos.blog.controller.api;
  
    import com.cos.blog.controller.dto.ResponseDto;
    import com.cos.blog.model.User;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
  
    @RestController
    public class UserApiController {
        @PostMapping("/api/user")
        public ResponseDto<Integer> save(@RequestBody User user){
            System.out.println("호출 ㅇㅋ");
            //숫자를 그냥 200으로 적는것 보단
            //HttpStatus를 이용해 성공값을 넣어주는게 더 안전
            return new ResponseDto<Integer>(HttpStatus.OK,1);
            //return 1;
            //return 1이면 ajax done의 resp값이 1이다.
        }
    }

```
**resources/static/js/user.js**

```javascript
    let index = {
        init: function() {
            $("#btn-save").on("click", () => {
                this.save();
            });
        },
        save: function() {
            //alert('user의 save함수');
            let data = {
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val()
            }
            //console.log(data);
            $.ajax({
                type : "POST",
                url : "/blog/api/user",
                //user라는 table에 data를 넣을것이기에 api/user까지만 적자.
                data : JSON.stringify(data), //데이터를 json으로 변경
                contentType: "application/json; charset=utf-8",
                dataType : "json", // 요청에 대한 응답이 왔을때의 데이터가 string인데 이걸 javascript objcet로 저장
                //회원가입 수행 요청
            }).done(function(resp){
                //성공시 done
                alert("회원가입이 완료 되었습니다.")
                location.href = "/blog";
            }).fail(function(error){
                //실패시 fail
                alert(JSON.stringify(error));
            }); //ajax통신을 이용해서 3개의 파라 데이터를 json으로 변경후 insert 요청
          
        }
    }
  
    index.init();
```
이번 강의에서 정말 중요한건 Dto를 설정하는것. 코드 보면서 다시 복습하자.

---

## 39강 - 회원가입 세팅 2

회원가입을 위해 Service를 등록해서 사용할 것임.
Service를 이용하는 이유는 **트랜젝션을 관리하기 위해서임. 여러 트랜젝션을 하나의 트랜젝션으로 관리할수 있는게 서비스.**

**com.cos.blog.Service.UserService.java**

* @Service를 통해 bean에 등록해주고, 사용시 @Autowired를 통해 의존성을 주입하고 사용하자.

```java
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
        try {
          userRepository.save(user);
          return 1;
        } catch (Exception e){
          e.printStackTrace();
          System.out.println("User Service: 회원가입() : " + e.getMessage());
          return -1;
        }
      }
    }
```
**com.cos.blog.controller.api.UserApiController.java**

* Service를 통해 트랜젝션을 관리하면 코드가 깔끔하고, 가독성도 좋아진다.

```java
    @RestController
    public class UserApiController {
  
        @Autowired
        private UserService userService;
        @PostMapping("/api/user")
        public ResponseDto<Integer> save(@RequestBody User user){
            System.out.println("호출 ㅇㅋ");
            user.setRole(RoleType.USER);
            int result = userService.회원가입(user);
            return new ResponseDto<Integer>(HttpStatus.OK,result); //1이면 성공 -1이면 실패
        }
    }
```
---

## 40강 - RespondDto 수정

앞서 강의에서 우리는 Exception handler를 지정한 바 있다.
그렇기에 UserService에서 지정한 try, catch가 제대로 동작하지 않고 GlobalExceptionHandler에 정의한
@ControllerAdvice Annotation에 의해 자동으로 Exception이 전달된다.
그래서 우리는 이 try catch를 지우고, GloablExceptionHandler를 이용하기로 했다.

**com.cos.blog.Service.UserService.java**

```java
public int 회원가입(User user) {
    userRepository.save(user);
    return 1;
  }
```
**com.cos.blog.handler.GlobalExceptionHandler.java**

* 상태의 값을 표현해 주는 것이 더 좋기 때문에 type을 string으로 바꾸고 e.getMessage()를 넣어주어 오류를 받도록 하였다.

```java
public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
            return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        }
```
**com.cos.blog.controller.api.UserApiController.java**

* 따로 Exception을 다루지 않아도 userService.회원가입 method에서 발생한 Exception이 자동으로 GlobalExceptionHandler로 이동한다.

```java
public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("호출 ㅇㅋ");
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //1이면 성공 -1이면 실패
    }
```
**com.cos.blog.Dto.ResponseDto.java**

* 반환타입을 inteager로 바꿧기 때문에 status또한 int로 바꿔준다.

```java
public class ResponseDto<T> {
    int status;
    T data;
}
```
---

## ~45강 까지는 db격리수준과 osiv 기술에 대한 논의점인데, 링크와 구글링을 통해 스스로 공부하자

[https://1-7171771.tistory.com/150](https://1-7171771.tistory.com/150) -> osiv에 대해 정리

---

## 46강 - 전통적인 방식의 spring 로그인

전통적인 방식의 로그인 방식은 서비스를 지정하고 세션을 쏴서 프론트에서 받아오는 것.

**layout/loginForm.jsp**

* Ajax를 이용할 것이기 때문에 버튼을 밖으로 빼줌

```html
<div class="container">
    <form>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
    </form>
    <button id="btn-login" class="btn btn-primary">로그인</button>
</div>
```
**static/js/user.js**

* 회원가입과 동일하게 ajax 요청 보냄.

```javascript
init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-login").on("click",() => {
            this.login();
        });
    }
```
```javascript
login: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        }
        //console.log(data);
        $.ajax({
            type : "POST",
            url : "/blog/api/user/login",
            data : JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json",
            //로그인 수행 요청
        }).done(function(resp){
            //성공시 done
            alert("로그인 완료.")
            location.href = "/blog";
        }).fail(function(error){
            //실패시 fail
            alert(JSON.stringify(error));
        });
    }
```
**header.jsp**

* jstl 사용을 위해 라이브러리 추가

```
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- jstl 불러오기 -->
```
* jstl 문법을 이용해 로그인 분기
  * c:choose : 시작, c:when : if, c:otherwise : else

```html
<c:choose>
    <c:when test =  "${empty sessionScope.principal}">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/blog/user/loginForm">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/blog/user/joinForm">회원가입</a>
            </li>
        </ul>
    </c:when>
    <c:otherwise>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/blog/user/writeForm">글쓰기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/blog/user/userForm">회원정보</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/blog/user/logout">로그아웃</a>
            </li>
        </ul>
    </c:otherwise>
</c:choose>
```
**UserService.java**

* select만 하는 작업이라 Transactional이 필요없지만 정확성 증가를 위해 사용함.

```java
@Transactional(readOnly = true)
  public User 로그인(User user) {
    return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
  }
```
**UserRepository.java**

* JPA Naming 전략
* findByUsernameAndPassword 이 함수를 선언만 하면
* SELECT * FROM user WHERE username=? AND password=?, 이 상태의 쿼리가 동작을 하게 됨.

```java
public interface UserRepository extends JpaRepository<User,Integer> {
  User findByUsernameAndPassword(String username, String password);

  /* 이런식으로 정의한게 위와 동일하다.
  @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
  User login(String username,String password);
  */
}
```
**UserApiController.java**

* session에 키값을 넣어주는 과정이 굉장히 중요함.

```java
@PostMapping("/api/user/login")
public ResponseDto<Integer> longin(@RequestBody User user, HttpSession session){
    System.out.println("UserApiController : login호출됨");
    User principal = userService.로그인(user); //principal(접근주체)
    if(principal != null){
        session.setAttribute("principal",principal);
    }
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
}
```
---

## 49강 - 스프링 시큐리티로 로그인 해보자. (스프링 시큐리티 커스터마이징)

security로 로그인을 함에 있어서 앞으로 ajax로 로그인을 하지 않을것이기 때문에 ajax와 관련된 코드들은 전부 지워주자.
또한 SecurityConfig를 생성해 Spring Security를 커스터마이징 해주자

**com.cos.blog.controller.config.SecurityConfig.java**

```java
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//아래 세 annotation은 세트이다. 자세한건 코드의 주석 참고.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
              .antMatchers("/auth/**") //auth는 아무나 들어올수 있다. any matchers
              .permitAll()
              .anyRequest() //이외의 다르 모든 요청은
              .authenticated()//인증이 되어야함.
            .and()
              .formLogin()
              .loginPage("/auth/loginForm"); // 인증이 필요한 곳이 있다면 loginForm으로 이동하라
  }
}
```
**Ajax와 관련된 로그인 기능이 포함된 UserService, UserApiController,UserRepository 모두 로그인 기능은 주석처리 해주자. 또한 User.js에 있는 Ajax 로그인 함수도 주석처리**

---

## 50강 - 비밀번호 해쉬화

비밀번호는 보안상의 이유로 항상 암호화해서 저장되어야 한다.
그럴때 사용하는것이 BCryptPasswordEncode이다.

**com.cos.blog.controller.config.SecurityConfig.java**

* 필요할때 객체를 불러와 사용하는것도 가능하겠지만 @Bean을 통해 IOC로 객체를 등록해서 사용하는게 더 편리하다
* 이후에 사용할 곳에서 @Autowired를 작성한 후 가져와서 사용하면 된다.
* BCryptPasswordEncoder 을 통해 password를 encode 할 수 있다.

```java
@Bean 
public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
        }
```
**UserService.java**

* @Autowired로 IOC에 올라와 있는 객체를 그냥 이용할 수 있다.

```java
@Autowired
  private BCryptPasswordEncoder encoder;
  @Transactional //회원가입 전체의 서비스가 하나의 transaction으로 묶이게 됨. 성공시 commit, 실패는 rollback
  public int 회원가입(User user) {
    user.setRole(RoleType.USER);
    String rawPassword = user.getPassword();
    String encPassword = encoder.encode(rawPassword);
    user.setPassword(encPassword);
    userRepository.save(user);
    return 1;
  }
```
---

## 51강 - CSRF

이번 강의에서 csrf를 disable한 이유는
spring security가 요청시 csrf token이 없다면 그 요청을 막아버리기 때문.
spring security는 csrf 공격을 대비하기 위해 csrf token이 없는 요청을 받지 않기 떄문에 이걸 테스트 단계에서는 disable 하고 테스트 해야한다.
나중에 서비스 완성시 csrf token을 날리면서 요청을 해주면 된다.

---

## 52강 - 스프링 시큐리티 로그인 구현

스프링 시큐리티를 사용하면 Controller에서 login controller를 구현하지 않아도 된다.

**SecurityConfig.java**

* loginPage를 통해 권한이 필요한 곳에 로그인이 안되있다면 자동으로 로그인 페이지로 이동하게 만들고
* loginProcessingUrl을 통해 Post요청을 받을 url을 지정해준다
* 로그인 성공시 defaultsuccessful로 지정된 url로 이동하며, 실패시 fail로 설정할순 있지만 따로 다루진 않는다.

```java
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
              .loginPage("/auth/loginForm") // 인증이 필요한 곳이 있다면 loginForm으로 이동하라
              .loginProcessingUrl("/auth/loginProc")
              .defaultSuccessUrl("/")//여기 process를 살펴보면, 인증이 되지 않으면 로그인으로 가라. 로그인 post요청은 auth/loginProC로 가라. 그리고 성공하면 메인으로 이동하라.
            ;
  }
```
**com.cos.blog.controller.config.auth.PrincipalDetail.java**

* 스프링 시큐리티에서 로그인하고 세션에 등록할때 필요한게 UserDetail type이다. 그렇기에 UserDetail을 리턴해주는 PrincipalDetail을 설정해줘야한다.
* 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장을 한다.

```java
package com.cos.blog.controller.config.auth;


import com.cos.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

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
    return collectors;
  }
}
```
**스프링 시큐리티가 로그인을 가로채서 대신 로그인을 할 때 Password가 어떻게 해쉬화 되었는지를 알아야 복호화를 할수 있다.**
**따라서 이를 지정해주는 PrincipalDetailService도 필요하다**

**SecurityConfig.java**

* 스프링에서 제공하는 configure(AuthenticationManagerBuilder) 를 불러와 오버라이딩 해줘야 한다.

```java
@Bean //IOC가 됨. 필요할때마다 가져와서 쓰면 되는것.
public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
        }
      
@Autowired
private PrincipalDetailService principalDetailService;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
        //로그인 할 때 패스워드를 어떻게 인코드 했는지 식별해서 비교해줌.
        }
```
**PrincipalDetailService.java**

```java
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
```
**또한 우리는 userRepository에 findByUsername이라는 method가 없기 때문에 지정해 줘야 한다.**
**UserRepository.java**

```java
public interface UserRepository extends JpaRepository<User,Integer> {
  // SELECT * FROM user WHERE username=? 으로 동작함.
  Optional<User> findByUsername(String username);
}
```
이렇게 스프링 시큐리티를 이용해 로그인/로그아웃 동작을 수행할 수 있다.
----------------------------------------------------------------------

## 53강 - 글쓰기 작성

글쓰기를 작성하는 방법은 User를 등록하는것과 전혀 다르지 않다
여기서는 summernote를 이용해 글쓰기 form을 가져왔고 이외의 form은 user와 동일하게 했다.
또한 글쓰기 데이터 전송도 joinuser와 동일한 json 타입의 데이터 전송으로, ajax를 이용했다.

글쓰기 model에는 글 제목, 내용 이외에 user타입을 전송해줘야 하는데, 이는 세션에 저장된 PrincipalDetail을 이용해 가져왔다.

**BoardApiController.java**

```java
@RestController
public class BoardApiController {

  @Autowired
  private BoardService boardService;

  @PostMapping("/board")
  public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
    System.out.println("글 작성 호출 완료.");
    boardService.글쓰기(board,principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
}
```
이외의 Service, Repository는 동일하다
**BoardService.java**

```java
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
}
```
**BoardRepository.java**

```java
public interface BoardRepository extends JpaRepository<Board ,Integer> {
}
```

-----------------------------------------------------

## 54강 - 글목록 보기

글쓰기를 완료했으니, index에 글 목록을 가져와보자.

글 목록을 가져올때 핵심적인 방식이 viewResolver의 특성을 이용해 view에 데이터를 넘겨주는 것이다

**BoardController.java**

* 여기서 model은 일반적인 request의 역할을 수행하고, boardService의 글목록 함수의 return값을 boards라는 변수명에 담아서 보내줌.

```java
    @GetMapping({"","/"})
    public String index(Model model) {
        model.addAttribute("boards",boardService.글목록());
        return "index"; //viewResolver가 작동해서, 해당 view에 정보가 전송이됨.
    }
```

**BoardService.java**

* 글목록은 간단하게 finAll을 리턴.
* 단순히 select만 하는 것이기 때문에 Transactional 필요없음.

```java
public List<Board> 글목록(){
    return boardRepository.findAll();
  }
```

**index.jsp**

* jstl의 기초 문법을 이용해 받아온 request를 출력하자.

```jsp
<c:forEach var="board" items="${boards}">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="#" class="btn btn-primary">상세 보기</a>
        </div>
    </div>
</c:forEach>
```

------------------------------------------

## 55강 - 글 목록 페이징 하기

페이징을 하기 위해서는 spring에서 제공하는 Pageable 객체를 이용한다. Pageable 객체에는 page정보와, data정보가 따로 담겨져 있으니, 원하는 정보를 얻기 위해선 getContent()를 이용해야한다.

**BoardController.java**

* @PageableDefault로 페이징할 갯수, 정렬 기준, 오름차순인지 내림차순인지 정해 주면 된다.

```java
@GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards",boardService.글목록(pageable));
        return "index";
    }
```

**BoardService.java**

```java
public Page<Board> 글목록(Pageable pageable){
    return boardRepository.findAll(pageable);
  }
```

**index.jsp**

* jstl 문법을 이용해 Page 객체 데이터를 받아온 boards에서 그 page값이 first인지 last인지에 따라 버튼을 활성화/비활성화 해준다.

```jsp
<div class ="container">
    <!-- jstl의 en표현식, request 받을때 boards가 날라옴. -->
    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="#" class="btn btn-primary">상세 보기</a>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
```



--------------------------------

## 56강 - 글 상세보기

상세보기는 어렵지 않다. 이전에서 사용했던 findById를 통해 글을 찾고, 불러오면 된다.

**BoardService.java**

```java
public Board 글상세보기(int id){
    return boardRepository.findById(id).orElseThrow(()->{
    return new IllegalArgumentException("글 상세보기 : 아이디 찾기 실패.");
    });
}
```

**BoardController.java**

* /board/{id} -> 쿼리스트링 -> @PathVariable로 받아오기 필요.

```java
@GetMapping("/board/{id}")
    public String fintByID(@PathVariable int id,Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
 	}
```

**index.jsp**

* 현재 board의 id를 쿼리스트링으로 넘겨주자

```jsp
<a href="/board/${board.id}" class="btn btn-primary">상세 보기</a>
```



**detail.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <button id="btn-update" class="btn btn-warning">수정</button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    <br/><br/>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr>
    <div class="form-group">
        <div>
            ${board.content}
        </div>
    </div>
    <hr>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
```

--------------------

## 57강 - 글 삭제하기

**detail.jsp**

* 세션에 있는 사용자와 글 작성자가 다르면 삭제 버튼이 보이면 안된다. 그래서 c:if문을 통해 세션과 board의 사용자를 체크해준다
* 이 방법은 프론트 단에서만 가능하고, 콘솔에서 함수 조작으로 삭제시킬수 있으므로, 서비스의 글 삭제하기에서 Principal의 user와 board의 user를 비교 후 삭제하는 함수를 추가해줘야 된다. 귀찮아서 안함.

```jsp
<c:if test="${board.user.id == principal.user.id}"><button id="btn-delete" class="btn btn-danger">삭제</button></c:if>
    <br/><br/>
    <div>
        글 번호 : <span id="boardId"><i>${board.id}</i></span>
        작성자  : <span><i>${board.user.username}</i></span>
    </div>
    <br/>
```

**board.js**

* 똑같이 Ajax를 사용해서 delete요청 처리. 

```javascript
$("#btn-delete").on("click",()=>{
            this.deleteById();
        });
```

```javascript
deleteById : function(){
        var id = $("#boardId").text();
        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            dataType: "json"
        }).done(function(resp){
            alert("삭제 완료");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        })
    }
```

**BoardService**

* 삭제함수는 간단하지만, 위에서 언급했듯, 보안상의 문제도 신경써야하니 principal에 등록된 session 객체와 board의 user가 다르면 삭제가 불가능하게끔 만들어 주긴 해야한다.

```java
@Transactional
  public void 글삭제하기(int id){
    boardRepository.deleteById(id);
  }
```

**BoardApiController.java**

```java
@DeleteMapping("/api/board/{id}")
  public ResponseDto<Integer> deleteById(@PathVariable int id){
    boardService.글삭제하기(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
```

----------------------------------

## 59강 - 글 수정하기

글 수정하기는 Update로 하는것이며, 더티체킹을 이용해 별도의 save과정없이 진행할 수 있다.

글 수정하기 또한 삭제하기와 마찬가지로 서버단에서 session의 id와 board의 user id와 비교하는 과정이 필수적으로 있어야 하지만, 여기선 따로 다루지 않았고 프론트단에서 버튼만 보이지 않게끔 해주었다.

**updateForm.jsp**

* hidden 태그로 감춰준 board의 id를 javascript에서 사용하기 위해 추가해주었다.

```jsp
<input type="hidden" id="updateId" value="${board.id}">
```

**board.js**

* 언제나 그렇듯 ajax 코드는 간단하게 위와 동일하다 다만, id값을 불러올때 html 태그 내의 value안에 있는 값은 val()로, 태그 내부 텍스트는 text()로 불러온다.
* type의 경우 자바스크립트에서는 자동으로 변환이 된다. "123"의 경우 int로, "123____"의 경우 string으로 자동 변환.

```javascript
$("#btn-update").on("click",()=>{
            this.updateById();
        });
```

```javascript
updateById: function() {
        let id = $("#updateId").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        console.log(id,data);
        $.ajax({
            type : "PUT",
            url : "/api/board/"+id,
            data : JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("글 수정 완료.")
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    },
```

**BoardController.java**

```java
@GetMapping("/board/{id}/updateForm")
    public String UpdateForm(@PathVariable int id, Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/updateForm";
    }
```

**BoardApiController.java**

```java
@PutMapping("/api/board/{id}")
  public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
    System.out.println("수정하기 컨트롤러 호출.");
    boardService.글수정하기(id,board);
    System.out.println("수정하기 끝남.");
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
```

**BoardService.java**

* 여기서 따로 repository에서 save를 하지 않은 이유는, 더티체킹으로 알아서 함수 종료시 save 되기 때문.

```java
@Transactional
  public void 글수정하기(int id,Board requestBoard){
    Board board = boardRepository.findById(id).orElseThrow(()->{
      return new IllegalArgumentException("글 찾기 실패 : 아이디 찾을수 없음.");
    });
    board.setTitle(requestBoard.getTitle());
    board.setContent(requestBoard.getContent());
    System.out.println("수정하기 service 호출됨");
  }
```

---------------------------------------------------------------------------------------------

## 61~62 - 회원 수정하기 및 세션 직접 수정하기.

이번 강의에서는 User데이터를 수정하는 과정을 거쳤다.

회원 데이터를 수정하는건 글을 수정하는것과 같이 더티체킹을 이용해 수정하여고, 글 수정하기에서는 쿼리스트링을 이용해 id값을 가져왔지만, 회원수정에서는 JSON 데이터를 쏴서 가입하였다. 그러나, 실제로는 쿼리스트링을 이용해 url을 구분하는 식으로 하는 경우가 더 많다.

**user/updateForm.jsp**

* joinForm에서 가져온것 그대로 사용.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">
    <!-- <form action="/user/join" method="POST"> 을 사용하지 않을것-->
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">수정 완료</button>
</div>
<!-- static 경로는 알아서 찾아감 -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
```

**user.js**

* ajax이용

```javascript
$("#btn-update").on("click",() =>{
            this.updateById();
        });
```

```javascript
updateById: function() {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        $.ajax({
            type : "PUT",
            url : "/user",
            data : JSON.stringify(data), //데이터를 json으로 변경
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("회원수정이 완료 되었습니다.")
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });

    }
```

**UserController.java**

* request body를 통해 id를 가져왔기 때문에 url에 {id}가 없는것 확인.

```java
@GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }
```

**UserApiController.java**

* json data(get이외의 요청)은 RequestBody에 담겨서 온다.

```java
@PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        System.out.println("usercontorller : update 호출 :" + user.getId());
        userService.회원수정(user);
        }
```

**UserService.java**

```java
@Transactional
  public void 회원수정(User user){
    User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
      return new IllegalArgumentException("유저 아이디 찾기 실패.");
    });
    String rawPassword = user.getPassword();
    String encPassword = encoder.encode(rawPassword);
    persistance.setPassword(encPassword);
    persistance.setEmail(user.getEmail());

  }
```



하지만 이렇게만 적용하면 회원수정 후 회원정보에 들어갔을때 Email이 바뀌지 않은 것을 확인 할 수 있다.

Mysql Workbench에서는 잘 적용되어 있는데 왜 적용이 되지 않은 걸까?

이는 세션이 갱신되지 않아서이다. 회원정보는 수정되었지만, 브라우저에서 가지고 있는 세션 데이터는 아직 수정 전 상태이기 때문에, 우리는 이를 직접 갱신해 주거나 아니면 강제로 로그아웃 시키는 방법을 이용해야한다.

우리는 세션을 직접 갱신시키는 방식을 이용하도록 하겠다. SpringSecurity를 수정하자.

**SecurityConfig.java**

* AuthenticationManager를 override해서, Bean으로 등록해 IOC 처리를 해주자.

```java
@Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
```

**UserApiController.java**

* Authentication 객체를 이용해 session에 새로운 UsernamePassword토큰을 넣어주고
* SecurityContextHolder를 통해 새로운 세션을 등록해 준다.

```java
@PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user,
                                       @AuthenticationPrincipal PrincipalDetail principal,
                                       HttpSession session){
        System.out.println("usercontorller : update 호출 :" + user.getId());
        userService.회원수정(user);
        //세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //서비스 종료시 세션 새로 등록.
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
```

이렇게 하면 세션이 새롭게 저장된다,



[스프링부트 with JPA 블로그 13강 - 시큐리티 동작 원리 :: GetInThere (tistory.com)](https://getinthere.tistory.com/29?category=884180)

Security의 동작 원리에 대해서는 여기를 참고하자.

--------------------------------

## 62~65강 -> 카카오 로그인 구현.

카카오 로그인을 구현하기에 앞서, 카카오 로그인 API를 가져와야 한다.

**[Kakao Developers](https://developers.kakao.com/)** -> 카카오 api 등록

* 로그인 -> 내 애플리케이션 -> 애플리케이션 추가하기 -> 작성후 등록.
* 앱 키 확인 -> 이 프로젝트에선 REST API키 등록.
* 옆에 메뉴 -> 카카오 로그인 -> 활성화, Redirect URI 등록 (http://localhost:8080/auth/kakao/callback)
  * 스프링 시큐리티를 이용하기 때문에, 로그인 화면에는 인증없이 들어가기 위해 auth를 추가해줌.
* 동의 항목에서 닉네임, 프로필사진, 이메일을 등록해주자
  * 이메일은 선택 동의만 가능하다.
  * 다른 항목은 필요에따라 체크해주면 된다.

* 사용 준비 완료.

사용 준비를 맞췃고 callback 함수를 설정했으니 이를 어떻게 사용할지 알아야 한다.

callback 함수가 작동하는걸 확인하기 위해 UserController에 get요청을 추가해주자.

카카오 로그인 아이콘은 **도구 - 리소스 다운로드** 에서 다운이 가능하다

**UserController.java**

```java
@GetMapping("/auth/kakao/callback")
public @ResponseBody String kakaoCallback(String code){
    return "콜백 요청 완료"
}
```

잘 작동하는 것을 확인했으면, 이제 kakao api에 POST요청을 보내 정보를 가져오자.

이와 관련된 메뉴얼은 **문서 - 카카오 로그인 - REST API** 에서 확인이 가능하다. 

#### Request

##### URL

```http
GET /oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code HTTP/1.1
Host: kauth.kakao.com
```

##### Parameter

| Name          | Type     | Description                                                  | Required |
| :------------ | :------- | :----------------------------------------------------------- | :------- |
| client_id     | `String` | 앱 REST API 키 [내 애플리케이션] > [앱 키]에서 확인 가능     | O        |
| redirect_uri  | `String` | 인가 코드가 리다이렉트될 URI                                 | O        |
| response_type | `String` | `code`로 고정                                                | O        |
| state         | `String` | 카카오 로그인 과정 중 동일한 값을 유지하는 임의의 문자열(정해진 형식 없음) [Cross-Site Request Forgery(CSRF)](https://en.wikipedia.org/wiki/Cross-site_request_forgery) 공격으로부터 카카오 로그인 요청을 보호하기 위해 사용 각 사용자의 로그인 요청에 대한 `state` 값은 고유해야 함 인가 코드 요청, 인가 코드 응답, 토큰 발급 요청의 `state` 값 일치 여부로 요청 및 응답 유효성 확인 가능 | X        |
| prompt        | `String` | 동의 화면 요청 시 추가 상호작용을 요청하고자 할 때 전달하는 파라미터 쉼표(,)로 구분된 문자열 값 목록으로 전달  다음 값 사용 가능: `login`: 기존 사용자 인증 여부와 상관없이 사용자에게 카카오계정 로그인 화면을 출력하여 다시 사용자 인증을 수행하고자 할 때 사용, 카카오톡 인앱 브라우저에서는 이 기능이 제공되지 않음 `none`: 사용자에게 동의 화면과 같은 대화형 UI를 노출하지 않고 인가 코드 발급을 요청할 때 사용, 인가 코드 발급을 위해 사용자의 동작이 필요한 경우 에러 응답 전달 | X        |
| nonce         | `String` | [OpenID Connect](https://developers.kakao.com/docs/latest/ko/kakaologin/common#oidc)를 통해 ID 토큰을 함께 발급받을 경우, [ID 토큰 재생](https://en.wikipedia.org/wiki/Replay_attack) 공격을 방지하기 위해 사용 [ID 토큰 유효성 검증](https://developers.kakao.com/docs/latest/ko/kakaologin/common#oidc-id-token-verify) 시 대조할 임의의 문자열(정해진 형식 없음) | X        |

\* auth_type: Deprecated, prompt를 사용하도록 변경

#### Response

| Name              | Type     | Description                                                  | Required |
| :---------------- | :------- | :----------------------------------------------------------- | :------- |
| code              | `String` | [토큰 받기](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token) 요청에 필요한 인가 코드 | X        |
| state             | `String` | 요청 시 전달한 `state` 값과 동일한 값                        | X        |
| error             | `String` | 인증 실패 시 반환되는 에러 코드                              | X        |
| error_description | `String` | 인증 실패 시 반환되는 에러 메시지                            | X        |

먼저, 우리는 로그인 Form을 작성해 kakao login API을 GET요청으로 불러와야 한다.

**loginForm.jsp**

* 로그인 버튼 옆에 a태그로 **Host/GET요청 주소** 를 담아준다.
* ${REST_API_KEY},${REDIRECT_URI} 에는 처음 설정한 key와 URI를 넣어주자.
* 그 후 실행해 api 호출이 되는지 확인한다.
* 이외의 다양한 field가 문서에 등록되있는데, 위의 예시처럼 쿼리스트링으로 넣어서 담아주면 되지만, 여기서는 사용하지 않는다.
* 이후 kakao api 호출에 성공하면, 주소에 쿼리스트링으로 code값이 주어진다. 이를 이용해 토큰 요청을 보내고, 로그인 정보를 가져오게 된다.

```jsp
<button id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=8486dbad96748f7d1e942c5a6ac3c7b2&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code">
            <img height="38px" src="/image/kakao_login_button.png">
        </a>
```

#### Request

##### URL

```http
POST /oauth/token HTTP/1.1
Host: kauth.kakao.com
Content-type: application/x-www-form-urlencoded;charset=utf-8
```

##### Parameter

| Name          | Type     | Description                                                  | Required |
| :------------ | :------- | :----------------------------------------------------------- | :------- |
| grant_type    | `String` | `authorization_code`로 고정                                  | O        |
| client_id     | `String` | 앱 REST API 키 [내 애플리케이션] > [앱 키]에서 확인 가능     | O        |
| redirect_uri  | `String` | 인가 코드가 리다이렉트된 URI                                 | O        |
| code          | `String` | 인가 코드 받기 요청으로 얻은 인가 코드                       | O        |
| client_secret | `String` | 토큰 발급 시, 보안을 강화하기 위해 추가 확인하는 코드 [내 애플리케이션] > [보안]에서 설정 가능 ON 상태인 경우 필수 설정해야 함 | X        |

#### Response



| Name                     | Type      | Description                                                  | Required |
| :----------------------- | :-------- | :----------------------------------------------------------- | :------- |
| token_type               | `String`  | 토큰 타입, `bearer`로 고정                                   | O        |
| access_token             | `String`  | 사용자 액세스 토큰 값                                        | O        |
| id_token                 | `String`  | [ID 토큰](https://developers.kakao.com/docs/latest/ko/kakaologin/common#oidc-id-token) 값 OpenID Connect 확장 기능을 통해 발급되는 ID 토큰, Base64 인코딩 된 사용자 인증 정보 포함  제공 조건: [OpenID Connect](https://developers.kakao.com/docs/latest/ko/kakaologin/common#oidc)가 활성화 된 앱의 토큰 발급 요청인 경우 또는 `scope`에 `openid`를 포함한 [추가 항목 동의 받기](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#additional-consent) 요청을 거친 토큰 발급 요청인 경우 | X        |
| expires_in               | `Integer` | 액세스 토큰과 ID 토큰의 만료 시간(초)  참고: 액세스 토큰과 ID 토큰의 만료 시간은 동일 | O        |
| refresh_token            | `String`  | 사용자 리프레시 토큰 값                                      | O        |
| refresh_token_expires_in | `Integer` | 리프레시 토큰 만료 시간(초)                                  | O        |
| scope                    | `String`  | 인증된 사용자의 정보 조회 권한 범위 범위가 여러 개일 경우, 공백으로 구분  참고: [OpenID Connect](https://developers.kakao.com/docs/latest/ko/kakaologin/common#oidc)가 활성화된 앱의 토큰 발급 요청인 경우, ID 토큰이 함께 발급되며 `scope` 값에 `openid` 포함 |          |

위에서 가져온 토큰을 통해 우리는 카카오 로그인 정보를 가져와야 한다.

Request에 필요한 parameter를 전달해줘야 한다. 그 후 반환되는 token 값들을 저장할 객체부터 만들어주자.

**model/OAuthToken.java**

* 필수적으로 return되는 type들을 저장하기 위한 OAuthToken을 만들어주고, Lombok을 이용해 getter, setter를 만들자.

```java
package com.cos.blog.model;


import lombok.Data;

@Data
public class OAuthToken {
  private String access_token;
  private String token_type;
  private String refresh_token;
  private int expires_in;
  private String scope;
  private int refresh_token_expires_in;
}
```



**UserController.java**

* RestTemplate로 http, post요청을 하자
* httpheaders로 필요한 헤더 정보를 저장한다.(Content-type)
* MultiValueMap에 필요한 파라미터 정보를 담아준다.
* response 객체에 반환값을 담아준다.
  * 이 반환값 안에는 access_token과 같은 타입들이 들어있다. 이는 json 타입이므로 이를 java객체로 변환해줘야 하는데 이때 사용하는게 ObjectMapping이다. (다양한 방식이 있다.)
* ObjectMapper를 이용해 미리 선언해준 oauthToken에 response 정보를 저장하자.
  * 이를 통해 다음번에 사용할 Token으로 로그인 정보를 가져오는 작업이 가능해 진다. getter를 이용해 access_token과 같은 필드의 값들을 다 가져올수 있기 때문이다.

```java
@GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){
        //@ResponseBody -> 데이터 리턴 함수
        //POST 방식으로 key value 타입의 데이터를 요청
        RestTemplate rt = new RestTemplate(); //http 요청을 간단하게 할수있는것.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        //전송할 데이터가 key,value임을 알려줌.

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","8486dbad96748f7d1e942c5a6ac3c7b2");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);

        //httpheader와 httpbody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        // Http 요청하기 = post방식 - 그리고 response 변수의 응답을 받음.
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // Gson, Json simple, ObjectMapper 라이브러리 중에 ObjectMapper사용.
        // json 데이터를 java 객체로 변환하는 작업. object mapper를 통해 response 데이터를 OAuthToken으로 변환해주자.
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken= null;
        try {
            oauthToken = objectMapper.readValue((String)response.getBody(),OAuthToken.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return ""+response.getBody();
    }
```



이후, 받은 token을 가지고 로그아웃과 사용자 정보를 가져오는 과정을 거쳐야 하는데

로그아웃 기능은 따로 구현하지 않겠다. (나중에 개인 프로젝트에서 해보자)

로그아웃 기능은 더이상 이 api로 접근이 불가능하게 토큰을 만료시키는 과정이기 때문에 필요하다. 하지만, 우리는 스프링시큐리티가 로그아웃을 해주고, 보안적인 부분을 따로 구현할 이유가 없기에 패스. 다만, 나중에는 꼭 해봐야함. 같은 토큰을 계속 두는건 보안상 위험.

#### Request: 액세스 토큰 사용

##### URL

```http
GET/POST /v2/user/me HTTP/1.1
Host: kapi.kakao.com
Authorization: Bearer ${ACCESS_TOKEN}
Content-type: application/x-www-form-urlencoded;charset=utf-8
```

##### Header

| Name          | Description                                                  | Required |
| :------------ | :----------------------------------------------------------- | :------- |
| Authorization | 사용자 인증 수단, 액세스 토큰 값 `Authorization: Bearer ${ACCESS_TOKEN}` | O        |

##### Parameter

| Name            | Type       | Description                                                  | Required |
| :-------------- | :--------- | :----------------------------------------------------------- | :------- |
| secure_resource | `Boolean`  | 이미지 URL 값 HTTPS 여부, true 설정 시 HTTPS 사용, 기본 값 false | X        |
| property_keys   | `String[]` | Property 키 목록, JSON Array를 ["kakao_account.email"]과 같은 형식으로 사용 | X        |

이제 Access_token을 이용해 사용자 정보를 가져올 것이다.

먼저, 이해를 돕기 위해 response값을 출력해보자.

**UserController.java**

* 필요한 헤더와 필드(여기선X)를 위와 동일한 방식으로 등록하자.

```java
//Access_token으로 사용자 정보 가져오기.
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        ResponseEntity response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
return response2.getBody();
```

이대로 실행해보면 response2의 값이 json형태의 데이터임을 알 수 있다. 그렇다면 위와 동일하게 이를 ObjectMapping을 이용해 java 객체로 변환해줘야하는데, 그러기 위해선 json 데이터와 일치하는 객체를 생성해줘야 한다.

이를 위해 사용하는 것이

[**jsonschema2pojo**](https://www.jsonschema2pojo.org/) 이것이다. 반환된 json data를 넣고 Preview를 클릭하면 객체를 자동으로 생성해주지만, 이를 어떻게 보기 좋게 만들지는 본인이 해야한다.

**model/kakaoProfile.java**

```java
package com.cos.blog.model;

import lombok.Data;

@Data
public class KakaoProfile {

  public Long id;
  public String connected_at;
  public Properties properties;
  public Kakao_account kakao_account;

  @Data
  static public class Properties {

    public String nickname;
    public String profile_image;
    public String thumbnail_image;

  }
  @Data
  static public class Kakao_account {

    public Boolean profile_nickname_needs_agreement;
    public Boolean profile_image_needs_agreement;
    public Profile profile;
    public Boolean has_email;
    public Boolean email_needs_agreement;
    public Boolean is_email_valid;
    public Boolean is_email_verified;
    public String email;

  }

  @Data
  static public class Profile {

    public String nickname;
    public String thumbnail_image_url;
    public String profile_image_url;
    public Boolean is_default_image;

  }

}

```

이 객체와 ObjectMapper를 이용해 데이터를 객체로 변환해주자. 그리고, 로그인을 수행하는데 여기서 key값을 설정하기 위해 application.yml에 필드를 추가해주자

**application.yml**

* 이 key값은 무조건 보호해야한다. 절대 남에게 알려주면 안된다.

```yaml
cos:
  key: cos1234
```

또한 카카오로 가입한 유저와, 일반 유저의 차이를 두기 위해서 User model도 수정해주자

**User.java**

* 카카오로 가입한 유저는 kakao를 넣어주고, 그냥 가입한 유저는 NULL로 유지.

```java
private String oauth; //kakao,google.
```



**UserController.java**

* Controller 전체 리소스
* @ResponseBody는 이제 사용하지 않을것이기에 지움.
* object mapper를 이용해 json data-> java object로 변환해주고 이를, kakaoUser의 Builder를 이용해 kakaoUser 객체에 저장해주자. 
  * 이 때 카카오 유저임을 구분하기 위해, username 뒤에 kakao user id를 추가하자.
  * password는 yml에서 설정한 공용 키 비밀번호인 cos1234를 이용한다.
* 마지막으로 회원을 저장한다
  * 이 떄, 중복된 username이 있으면 안되니 회원찾기 메소드를 UserService에서 추가하고, 이를 이용해 중복된 데이터가 있으면 회원가입 X, 없으면 회원가입을 진행한다.
  * 그리고 마지막으로 세션에 직접 로그인 정보를 등록해준다.

```java
@GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){
        //@ResponseBody -> 데이터 리턴 함수
        //POST 방식으로 key value 타입의 데이터를 요청
        RestTemplate rt = new RestTemplate(); //http 요청을 간단하게 할수있는것.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        //전송할 데이터가 key,value임을 알려줌.

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","8486dbad96748f7d1e942c5a6ac3c7b2");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);

        //httpheader와 httpbody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        // Http 요청하기 = post방식 - 그리고 response 변수의 응답을 받음.
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // Gson, Json simple, ObjectMapper 라이브러리 중에 ObjectMapper사용.
        // json 데이터를 java 객체로 변환하는 작업. object mapper를 통해 response 데이터를 OAuthToken으로 변환해주자.
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken= null;
        try {
            oauthToken = objectMapper.readValue((String)response.getBody(),OAuthToken.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        //System.out.println(oauthToken.getAccess_token());

        //Access_token으로 사용자 정보 가져오기.
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        ResponseEntity response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        //Objcet Mapper로 response2 객체를 자바 객체로 변경.
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue((String)response2.getBody(),KakaoProfile.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 사용자(이름) : " + kakaoProfile.getProperties().getNickname());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        // 이 정보로 username, password, email을 구성할 것.
        System.out.println("블로그 서버 유저 네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("블로그 서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(cosKey)
                .oauth("kakao")
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();

        User originUser = userService.회원찾기(kakaoUser.getUsername());
        if(originUser.getUsername() == null) {
            System.out.println("신규회원. 카카오 회원가입");
            userService.회원가입(kakaoUser);
        }
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
```

**UserService.java**

```java
  @Transactional(readOnly = true)
  public User 회원찾기(String username){
    User user = userRepository.findByUsername(username).orElseGet(()->{
      return new User();
    });
    return user;
  }
}
```

그리고 kakao user는 email과 password가 수정이 불가능 하게 Form과 Service를 수정해준다

**user/updateForm.jsp**

```jsp
<c:if test="${empty principal.user.oauth}">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" placeholder="Enter password" id="password">
            </div>
        </c:if>
        <c:if test="${not empty principal.user.oauth}">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" readonly>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" placeholder="Enter password" id="password" readonly>
            </div>
        </c:if>
```

**UserService.java**

* Oauth 값을 이용해 카카오 유저인지, 일반 유저인지 구분.

```java
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
```

-------------------------------------

## ~68강 - 댓글 생성

우리는 board를 생성하고 수정하는 과정에서 이미 board 객체를 프론트단에 전달해 주었기 때문에 (Controller에서 Get 요청시 Model 객체에 담아줌), 그곳에 포함된 reply 객체를 이용할수 있다. 

**BoardController.java**

```java
@GetMapping("/board/{id}")
    public String fintByID(@PathVariable int id,Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }
```

Board 객체는 Reply 객체를 가지고 있기 때문이다.

일단 우리는 Reply에 직접 데이터를 입력하고 댓글 출력을 먼저 해 볼 것이다.

**detail.jsp**

```jsp
<div class="card">
        <form>
            <input type="hidden" value="${board.id}" id="reply-boardId">
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br/>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.reply}">
                <li id="reply--1" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                        <button class="badge">삭제</button>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
```

여기서 reply의 user를 출력하는 **${reply.user.username}**를 보면 이 코드는 사실 **board.reply.user.username**이다. 비록, 지금 프로젝트는 db가 매우 적기 때문에 지금 당장에서야 아무런 문제가 되지 않는다.

그러나, db가 조금이라도 커지게 되면 이 방식은 매우 문제가 커지게 된다. 왜냐하면 **순환참조**가 일어나기 때문이다.

**Board.java**

```java
@OneToMany(mappedBy = "board",fetch = FetchType.LAZY)
@OrderBy("id desc")
private List<Reply> reply; 

@ManyToOne
@JoinColumn(name="userId")
private User user;
```



**Reply.java**

```java
@ManyToOne
@JoinColumn(name="boardId")
private Board board;

@ManyToOne
@JoinColumn(name="userId")
private User user;
```

이 두 모델을 보자. JPA의 특성상 한 객체의 데이터를 조회할때는 getter를 호출하게 되어있다. 그렇다면 **board.reply.user.username**을 호출하는 과정을 생각해보자

* board의 getter 호출 -> board의 모든 데이터 호출

* reply getter 호출 -> reply의 모든 데이터 호출 -> reply는 board,user 가지고 있음 -> 또 board,user getter 호출 -> 1번 반복

의 형태로 계속 같은 entity를 호출하게 되기 때문이다.

그래서 우리는 이걸 해결하기 위해 annotation 하나를 추가해 줄 것이다.

**Board.java**

* @JsonIgnoreProperties -> reply를 통해 데이터가 참조될 때, board객체를 무시하겠다는 뜻이다.
* 실제로 데이터를 출력해보면, reply객체 안에 board가 사라진걸 확인할 수 있다. 이러면 순환참조를 어느정도 방지할 순 있다. 그러나 Dto를 사용하거나 model을 잘 만드는게 제일 중요하다.

```java
@OneToMany(mappedBy = "board",fetch = FetchType.LAZY) //reply table에 있는 board를 넣어줌.
@JsonIgnoreProperties({"board"})
@OrderBy("id desc")
private List<Reply> reply;
```

이렇게 댓글을 불러오는 과정은 특별한 Service 생성이 없이 해결할 수 있었다.

이제 댓글을 생성해 보자.

**BoardApiController.java**

```java
@PostMapping("/api/board/{boardId}/reply")
  public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){
    System.out.println("댓글 작성 호출 완료.");
    boardService.댓글쓰기(principal.getUser(),boardId,reply);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
```

**BoardService.java**

```java
@Autowired
  private ReplyRepository replyRepository;
  @Transactional
  public void 댓글쓰기(User user, int boardId, Reply requestReply){
    requestReply.setUser(user);
    Board board = boardRepository.findById(boardId).orElseThrow(()->{
      return new IllegalArgumentException("댓글 쓰기 실패 : 게시글을 찾을 수 없습니다.");
    });
    requestReply.setBoard(board);
    replyRepository.save(requestReply);
  }
```

**ReplyRepository.java**

```java
package com.cos.blog.repository;

import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}

```

------------------------------------

## 69강 - 댓글 작성시 Dto 사용하기

Dto를 사용하면 원하는 데이터를 받아서 그것만 이용하기 때문에 코드 작성이 수월해질 수 있다.

또한, 이 목차와는 관련 없지만, Dto를 사용하는것이 순환참조를 애초에 막을수 있는 좋은 방법이다 .

왜냐하면 순환참조는 Entity 전체를 호출하는 과정에서 불필요한 객체까지 호출하고, 이 객체에서 또 다른 Entity를 호출하는 과정인데, Dto를 이용하면 원하는 필드값만 가져오니, 이런 오류를 애초에 방지할 수 있다.

**ReplySaveRequestDto.java**

```
package com.cos.blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
  private int userId;
  private int boardId;
  private String content;
}

```

**BoardApiController.java**

```java
@PostMapping("/api/board/{boardId}/reply")
  public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
    System.out.println("댓글 작성 호출 완료.");
    boardService.댓글쓰기(replySaveRequestDto);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
  }
```

**BoardService.java**

```java
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
```

**detail.jsp**

```jsp
<input type="hidden" value="${board.id}" id="reply-boardId">
            <input type="hidden" value="${principal.user.id}" id="reply-userId">
```

**board.js**

```javascript
replySave: function() {
        let data = {
            boardId:$("#reply-boardId").val(),
            userId: $("#reply-userId").val(),
            content: $("#reply-content").val()
        }
        $.ajax({
            type : "POST",
            url : `/api/board/${data.boardId}/reply`,
            data : JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("댓글 작성 완료.")
            location.href = `/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    }
```

