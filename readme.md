# 스프링 연습

------------------------------
## 1강 준비물

1. Oracle JDK 1.8 설치

> https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

-----------------------------

2. MySQL 설치

> https://dev.mysql.com/downloads/windows/installer/5.7.html

>> 알아서 최신버전으로 설치하자.

---------------------

3. 폰트 설치

> https://www.cookierunfont.com/#section7

>> TTF regular

-----------------

4. STS 툴 설치

> https://spring.io/tools

>> + 현재 VsCode로 설치해서 학습중.
>> + Eclipse로 바꿈..

---------------------------

5. 인텔리 J - 키맵 세팅

   Eclipse Marketplace - IntelliJ IDEA keymap for Eclipse 0.1

6. UTF-8 세팅

7. 폰트 세팅



---------------------------------
## 2강

### UTF - 8 쓰는 이유

입력 신호 -> 1 or 0
2^1 - > 2가지
2^2 - > 4가지
.
.
.
2^8 -> 256가지
> 2^8 = 8 bit = 1 byte
>> 만일 1byte로 문자를 표현한다 -> 한글(1byte), 중국어(3byte)와 같은 문자 깨짐.
>>> 그래서 3byte를 전세계 표준으로 이용하고, 이를 UTF-8이라 부름.

-------------------------

## 3강
### 의존성 패치

1. Spring Boot DevTools

> https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html

다양한 개발 편의를 제공해줌.
특히 Automatic restart 제공. -> 자동 재시작. 파일 변경시 자동으로 재시작 해주는기능.

2. Lombok

>https://projectlombok.org/

getter, setter같은것을 자동으로 생성해줌.

3. Spring Data JPA

DB를 JPA를 이용해 다룰 것이라 설치.

4. MySQL Driver

5. Spring Security

보안적인 기능 제공.

6. OAuth2 Client - 세팅안함.
>직접 노가다로 카카오 로그인 구현하여 개념잡기
>다음 인스타 프로젝트에서는 사용할 것임.
>JWT는 APP과 연동할 때 사용할 예정

7. 템플릿 엔진
>jsp 사용할 예정

8. Spring Web

>WEB MVC를 사용하여 웹 애플리케이션을 만드는데 필요한 스프링부트의 기본적인 요소를 가짐.
>내장형 컨테이너로 톰캣을 기본탑재하고 있다.

10. 추가 적인 것들
>https://mvnrepository.com/

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

----------------------

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

------------------

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

--------------------

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

    <dependency>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-resources-plugin</artifactId>
        <version>2.4.3</version>
    </dependency>

#### 그리고 프로젝트 우클릭 -> run as -> maven install
#### 이클립스 새로고침(F5)
#### 프로젝트 우클릭 -> maven -> update project를 해주니 해결되었다.

-------------------------------

## 7강 git 연동 -> 스킵.

------------------------------

## 8강 git의 추상적 개념

git -> 프로젝트 관리

------------------------------

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


-------------------------------

## 10강 Http1.1 설명 -> 스킵

------------------------------

## 11강 stateless란
> stateless방식은 한번의 요청과 응답이 이루어지면 클라이언트와 서버와의 연결선이 끊어지는 것을 말한다. 이 방식은 주로 http에 사용되는 방식이며 이는 서버의 부하를 줄여준다.

---------------------------------

## 12강 MIME 타입 -> 스킵 개별공부

--------------------------------

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

  ```
   public String getTest(Member m) {
      return "get 요청 : " + m.getId() + " , " + m.getUsername();
  }
  ```

###### POST요청 (PUT도 동일)
* POST요청은 데이터를 쿼리스트링이 아닌 body라는 곳에 담아서 보낸다.
* 이 방식은 3가지 정도로 나뉠수 있다 (더 많음)
* 1. form 태그로 받아오는 데이터
      ```
      public String postTest(Member m) {
          return "post 요청 : " + m.getId() + " , " + m.getUsername();
      }
      ```
    * 앞서 쿼리스트링에서 클래스를 이용해 데이터를 받아오는것과 동일하게 다루면 된다.

* 2. Text 타입 -> Text를 있는 그대로 출력

```
public String postTest(@RequestBody String text) {
    return "post 요청 : " + text;
}
```

* 3. JSON 타입 -> 스프링의 MessageConverter가 자동으로 클래스에 선언된 변수에 맞게 매핑해준다
```
public String postTest(@RequestBody Member m) {
    return "post 요청 : " + m.getId() + " , " + m.getUsername();
}
```

--------------------------------------

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

--------------------------------------

## 16강 Lombok 세팅

C\사용자\사용자명\.m2\repository -> maven이 관리하는 라이브러리(의존성)들

repository\org\projectlombook\lombook\버전 -> jar파일 cmd로 실행(git bash도 가능)후 idle을 sts로 설정후 닫기.

롬북을 이용하면 @Getter,@Setter를 이용해 getter,setter를 쉽게 생성 가능하고, 둘 다 생성하고 싶으면 @Data를 이용하자.
@AllArgsConstructor -> 생성자
