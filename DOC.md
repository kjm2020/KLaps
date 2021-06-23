1. SpringBoot 기초
▶ 스프링 부트란?
- 스프링 프레임워크를 사용하는 프로젝트를 아주 간편하게 설정할 수 있는 스프링 프레임웍의 서브 프로젝트라고 할 수 있다.

- Spring Boot makes it easy to create stand-alone.
  단독실행가능한 스프링애플리케이션을 생성한다.

- Most Spring Boot applications need very little Spring configuration.
  Spring Boot는 최소한의 초기 스프링 구성으로 가능한 한 빨리 시작하고 실행할 수 있도록 설계되었다.
- 웹 컨테이너를 내장하고 있어 최소한의 설정으로 쉽게 웹 어플리케이션을 만들 수 있다.

★ GroupId :  GroupId는 자신의 프로젝트를 식별해주는 고유 아이디.
                  'com.godDaeHee.web' 과 같이 인터넷 주소를 뒤집어 써놓은 형태.
★ ArtifactId : ArtifactId는 버전 정보를 생략한 이름(jar).
                  보통 프로젝트 ID와 동일하게 작성.

pom.xml
★ 2.1 Spring Boot Starter Parent
- 스프링부트(SpringBoot)에 필요한 dependency를 자동으로 추가.
- spring-boot-starter-parent 아티팩트를 parent 태그에 명시하면 spring-boot-dependencies-x.x.x.RELEASE.pom 파일이 상속됨.
  이에 사용자가 특별히 명시하지 않아도 스프링 부트에서 제공하는 의존성이 자동적으로 설정됨.
- 프로젝트 설정시 다양한 라이브러리를 사용하게되고, 버전 충돌문제가 늘 발생.
  starter-parent는 의존성 조합간의 충돌 문제가 없는 검증 된 버전정보 조합을 제공하여 충돌 문제를 해결하여 줌.
- 만일 spring-boot-starter-parent 로 상속받은 설정 정보를 다시 재정의하고 싶으면 다음과 같이 properties 태그에 명시.

깃헙이나 메이븐 리파지토리 사이트에서 상세 버전정보 조합을 확인.
- Maven repository - spring-boot-stater-parent
- GitHub - spring-boot-stater-parent

<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.2.4.RELEASE</version>
</parent>


★ 2.2 spring-boot-starter-web
-  Spring MVC를 사용한 RESTful서비스를 개발하는데 사용.

★ 2.3 spring-boot-starter-test
- Junit, Hamcrest, Mockito를 포함하는 스프링 어플리케이션을 테스트 가능하도록 함.

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>


gradle 사용시 build.gradle

plugins {
  id 'org.springframework.boot' version '2.2.6.RELEASE'
  id 'io.spring.dependency-management' version '1.0.9.RELEASE'
  id 'java'
}

group = 'com.god'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
  mavenCentral()
}

dependencies {
  implementation 'org.springframework.boot:spring-boot-starter'
  testImplementation('org.springframework.boot:spring-boot-starter-test') {
    exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
  }
  compile('org.springframework.boot:spring-boot-starter-web')
  testCompile('org.springframework.boot:spring-boot-starter-test')
}

test {
  useJUnitPlatform()
}


▶ 3. Application.java
- Application.java 소스를 확인해 보면 @SpringBootApplication 이란 어노테이션이 선언.
  ★ 3.1 @SpringBootApplication
  - 해당 어노테이션 하나가 @EnableAutoConfiguration, @ComponentScan, @Configuration을 하나로 묶어 놓은 거라고 볼 수 있음.
- 스프링부트를 기동하기 위해서는 main 메소드가 필요한데 여기에 SpringApplication.run(BoApplication.class, args); 
  이 부분이 들어감.

- 해당 annotation을 설정한 클래스가 있는 package를 최상위 패키지로 인식하고 ComponentScan을 수행하기 때문에
  해당 어노테이션이 있는 클래스의 파일 위치 또한 중요.

▶ 4. index.html
- 서버는 기동 되었지만, 현재 표현할 페이지가 없음.
- resources > static 하위에 index.html 파일을 생성.
- 그리고 다시 서버를 기동하면 내가 생성한 index.html 파일이 열림.
  (http://localhost:8080/index.html)


★ 4.1 정적 리소스
- Spring Boot 프로젝트는 별도의 커스터마이징이 없는 경우 정적 리소스의 위치.

◎ static
◎ public
◎ resources
◎ META-INF/resources

ex) META-INF/resources/test/test.txt 경로에 파일이 위치할 경우 HTTP 상의 요청 주소는 /test/test.txt 
------------------------------------------------------------------------------------------------------------
1. MVC
   ▶ 개요
- MVC(Model View Controller)란 하나의 디자인 패턴.
- 스프링 MVC : 스프링이 제공하는 웹 어플리케이션 구축 전용 MVC 프레임워크.
  1. 모델(Model) : 비즈니스 규칙을 표현
  2. 뷰(View) : 프레젠테이션을 표현
  3. 컨트롤러(Controller) : 위 두가지를 분리하기 위하여 양측 사이에 배치된 인터페이스

2. Controller
   ▶ Controller란?
- Controller에 대해 간단히 말하자면 MVC에서 C에 해당 하며 주로 사용자의 요청을 처리 한 후 지정된 뷰에 모델 객체를
  넘겨주는 역할.

▶ Controller 관련 대표적인 Annotation
1)  @Controller
- Controller의 역할을 수행 한다고 명시
  (해당 클래스를 Controller로 사용한다고 Spring FrameWork에 알림.)  
  필요한 비즈니스 로직을 호출하여 전달할 모델(Model)과 이동할 뷰(View) 정보를
  DispatherServlet에 반환.
- Bean으로 등록
- @Component의 구체화 된 어노테이션

2)  @RequestMapping
- 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지 맵핑하기 위한 어노테이션
- 클래스나 메서드 선언부에 @RequestMapping과 함께 URL을 명시하여 사용.
-  viewName 생략시 @RequestMapping의 path로 설정한 URL이 default viewName

RequestMapping 속성들
1) value(String[])  : URL 값
   /* EX) */
   @RequestMapping(value="/login")
   @RequestMapping("/login")

2) method (RequestMethod[]) : HTTP Request 메소드 값
- GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE

/* EX1) */
@RequestMapping(value="/login", method=@RequestMethod.GET)
/* EX2) */
@RequestMapping(value="/login", method=@RequestMethod.POST)
※ Spring4.3 이후 ex1), ex2)는 다음과 같이 사용가능.

ex1) @GetMapping("/login")
== @RequestMapping("/login", method=@RequestMethod.GET)
ex2) @PostMapping("/login")
== @RequestMapping("/login", method=@RequestMethod.POST)

3) params(String[]) : HTTP Request 파라미터
   ① @RequestParam : 사용자가 원하는 매개변수에 값을 매핑하기위해 사용.

/* EX1) */
@PostMapping("/member")
public String member(@RequestParam String name, @RequestParam Int age)
여기서 @ReauestParam은 생략 가능.
사용자가 입력한 key값과 매개변수의 이름을 비교하여 값을 넣어주기 때문.
결국 다음의 ex2)와 ex1)은 동일한의미다.

/* EX2) */
@PostMapping("/member")
public String member(String name, Int age)

② @PathVariable : url 경로를 변수화하여 사용할 수 있도록 함.

@RequestMapping("/member/{name}/{age}")
public String member(@PathVariable("name") String name, @PathVariable("age") String age)
=> RequestMapping의 {name}과 PathVariable 의 String name을 매핑 함.

4) consumes(String[]) : Request Body에 담는 타입을 제한.
   ex) @PostMapping("/login", consumes="application/json")
   헤더에 application/json이 존재 해야 처리.


▶ TestController 생성 (@Controller)

▶ View 생성 (INDEX 페이지 만들기)
- src/main/resources/static 경로에 index.html 파일을 추가.
  (static 폴더는 정적 html 문서, 이미지, 영상 등 관리)

- 혹시 이미 8080포트를 사용하고 있는 경우엔 포트 변경.
  /src/main/resources/application.properties
  server.port = 8000

▶ @ResponseBody 로 객체 정보 전달 하기
- view가 아닌 data를 반환해야 하는 경우라면 @ResponseBody를 사용.
- @ResponseBody 어노테이션을 통해 간단하게는 String, Map, JSON 등을 전달.

▶ @RestController 생성
- Spring 4.0이상은 @Controller와 @ResponseBody 어노테이션을 추가하는 것 대신 @RestController을 제공.
- @RestController를 사용하면 @ResponseBody를 추가 할 필요가 없고,
  @ResponseBody 어노테이션은 기본적으로 활성화되어 있다.

- @RestController를 디컴파일 해보면 @Controller와 @ResponseBody를 포함 하고 있는 것을 볼 수 있음.

  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @Controller
  @ResponseBody
  public @interface RestController {
  @AliasFor( annotation = Controller.class )
  String value()
  default "";
  } 

------------------------------------------------------------------------------------------------------------

- 스프링 부트는 가능하다면 jsp를 피하고 Thymeleaf와 같은 템플릿 엔진을 사용하라고 권장한다.
  https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-developing-web-applications

1. Spring Boot에서의 View
   ▶ 스프링 부트에서도 여러가지 뷰를 사용 가능.
   ㆍJSP/JSTL
   ㆍThymeleaf
   ㆍFreeMarker
   ㆍVelocity
   ㆍGroovy Template Engine
   ㆍTiles

... etc

src > main > reousrce > [static] 폴더엔 정적 리소스들을 추가.
src > main > reousrce > [templates] 폴더도 확인할 수 있을 것인데 Thymeleaf(.html), Velocity(.vm)등과 관련된 파일만 
동작하고 jsp 파일은 추가하여도 작동하지 않으니 참고.

※ 폴더 구조

src
└─ main
└─ resource
└─ templates (View: Thymeleaf, Groovy, Velocity 등)
└─ static    (정적 컨텐츠 : html, css, js, image 등)

▶ 내장 Tomcat
- 스프링부트는 웹 개발을 위해 자주 사용되는 Spring의 Component들과 Tomcat, Jetty 등의 경량 웹 어플리케이션 서버를 통합한
  경량의 웹개발 프레임 워크.
- 즉 별도의 웹 어플리케이션 서버 없이 SpringBoot를 통해 프레임워크와 웹 어플리케이션 서버를 통합.

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
spring-boot-starter-web 에는 tomcat이 포함되어 있지만, JSP 엔진은 포함하고 있지 않다.
하지만 간단한 설정만 해주면 JSP view를 사용 가능

"JSP"
2. pom.xml / gradle 설정
   ▶ pom.xml
- jasper,jstl을 의존성에 추가해야 JSP파일의 구동이 가능.
  (앞서 말했듯이 jsp 파일은 Springboot의 templates 폴더안에서 작동하지 않으니 참고.)

▶ build.gradle
compile('org.apache.tomcat.embed:tomcat-embed-jasper')
compile('javax.servlet:jstl:1.2')

3. JSP  경로 설정(디렉토리 생성)
   ▶ 스프링 부트에서도 여러가지 뷰를 사용 가능.
  - WEB-INF/jsp/(또는 WEB-INF/views/)
    (full 경로 : /src/main/webapp/WEB-INF/jsp/)
- 톰캣기반 자바 웹어플리케이션에서는 보안상 jsp 위치를 URL로 직접접근할 수 없는 WEB-INF폴더 하위에 위치.

ex) test.jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!DOCTYPE html>
  <html lang="ko">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>View Test Page</title>
  </head>
  <body>
      <h2>Hello! ${name}</h2>
      <div>JSP List Test</div>
          <c:forEach var="item" items="${list}" varStatus="idx">
          ${idx.index}, ${item} <br />
          </c:forEach>
  </body>
  </html>

4. application.properties
   ※ Spring 애플리케이션 시작시 application.properties 파일에 정의된 내용을 로드.
   (스프링부트의 AutoConfiguration을 통해 자동 설정한 속성값들이 존재하며, application.properties의 해당 
   값들은 오버라이드.)

▶ server.port
- 별다른 설정을 하지 않으면 default 포트는 8080.
- Spring Boot에 기본적으로 내장되어있는 Tomcat과 Jetty와 같은 WAS의 포트번호를 임의로 변경 가능.

server.port = 8888
▶ prefix/suffix
- jsp 페이지를 처리하기 위한 prefix와 suffix를 application.properties에 추가 하자.
- 앞서 생성한 JSP 경로를 prefix로 선언, 그리고 확장자럴 suffix로 선언할 수 있다.

spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

5. Controller 설정
   @RequestMapping("/test")
   public ModelAndView test() throws Exception{
   ModelAndView mav = new ModelAndView("test");
   mav.addObject("name", "goddaehee");

   List<String> testList = new ArrayList<String>();
   testList.add("a");
   testList.add("b");
   testList.add("c");

   mav.addObject("list", testList);
   return mav;
   }


6. jsp파일 서버 재시작 없이 바로 적용하기
- 스프링 부트는 스프링 프로젝트와 다르게, 동적 파일들의 파일 변경을 자동으로 반영하지 않는다.
- 기존 스프링과 동일하게 자동 반영하기 위해선 다음과 같은 설정을 추가하여 주면 된다.
- Spring Boot 2.x 버전 기준.

▶ pom.xml
  <dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  </dependency>
▶ application.properties
devtools.livereload.enabled=true
※참고 Spring Boot 1.x 버전 기준으로는 application.properties에 다음 내용을 추가.
server.jsp-servlet.init-parameters.development=true


◎ Application을 실행.

"thymeleaf"
7. thymeleaf
   Thymeleaf 홈페이지
- https://www.thymeleaf.org/index.html

Thymeleaf + Spring
- https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html



▶ Dependency 추가
- pom.xml

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>


- build.gradle

implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
▶ 파일 기본 경로 및 html 생성
- 경로 : /src/main/resources/templates/thymeleaf

- thymeleafTest.html

<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello thymeleaf</title>
</head>
<body>
	<h1>Hello thymeleaf</h1>
	<h2>name = <span th:text="${testModel.name}"></span></h2>
	<h3>id =  <span th:text="${testModel.id}"></span></h3>
</body>
</html>

▶ application.properties 설정 
#JSP와 같이 사용할 경우 뷰 구분을 위해 컨트롤러가 뷰 이름을 반환할때 thymeleaf/ 로 시작하면 타임리프로 처리하도록 
view-names 지정

spring.thymeleaf.view-names=thymeleaf/*
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
#thymeleaf를 사용하다 수정 사항이 생길 때 수정을 하면 재시작을 해줘야 한다. 이를 무시하고 브라우저 새로고침시 수정사항 반영을 취해 cache=false 설정(운영시는 true)
spring.thymeleaf.cache=false
spring.thymeleaf.check-template-location=true
▶ Vo 설정 
package com.god.bo.test.vo;

public class TestVo {
private Long mbrNo;
private String id;
private String name;

    public TestVo() {
    }

    public TestVo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getMbrNo() {
        return mbrNo;
    }

    public void setMbrNo(Long mbrNo) {
        this.mbrNo = mbrNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
▶ Controller 설정
@RequestMapping("/thymeleafTest")
public String thymeleafTest(Model model) {
TestVo testModel = new TestVo("hongkildong", "홍길동") ;
model.addAttribute("testModel", testModel);
return "thymeleaf/thymeleafTest";
}




------------------------------------------------------------------------------------------------------------

[ SpringBoot  DB 연동]


------------------------------------------------------------------------------------------------------------

▶ 커넥션풀(Connection Pool)이란?
1) 정의
- 풀(Pool)속에 데이터베이스와의 연결(커넥션)들을 미리 만들어 두고 데이터베이스에 접근시 풀에 남아있는 커넥션중 하나를 
  받아와서 사용한뒤 반환하는 기법.
- DataBase Connection Pool, DBCP라고도 한다.

2) 사용이유
- 웹 애플리케이션은 다수의 사용자가 데이터베이스에 접근해야 하는 상황에 사용자들이 요청할때마다 연결을 만들고 해제하는
  과정을 진행하게되면 비효율적이다.
  따라서 커넥션풀을 이용하여 미리 여러 연결을 만들어놓고 필요한 사용자가 요청시 미리 만들어놓은 연결을 주는 형식으로 
  효과적으로 DB연결 및 자원사용을 할 수 있다.

▶ Dependency 추가



Spring Boot에 JDBC를 통해 mariadb(mysql) 연결해보자!


사실 특별한 설정이 필요하진 않다. 역시 스프링 부트 하다.
Dependency와 application.properties에 간단한 설정만 하면 MyBatis 및 MariaDB 연결은 완료 된다. (1 ~ 2번)
앞서 포스팅한 MVC의 기본 개념들을 적용하여 화면에 출력하는 것 까지 간단한 예제로 정리해 보려 한다.















