### Properties file(yml)

이전에 접하지 못했던 개념이라 정리한다.

properties란

+ 애플리케이션의 구성 가능한 파라미터들을 저장하기 위해 자바 관련 기술을
사용하는 파일들을 위한 파일 확장.

+ 키 : 값에 해당하는 형태로 이루어짐
+ 각 라인은 일반적으로 하나의 property를 저장

예시는 다음과 같다.

    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.url= jdbc:h2:tcp://localhost/~/jpashop
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.show-sql=true
    spring.jpa.database=h2
    spring.jpa.hibernate.ddl-auto=none

##### 부연설명
+ spring.jpa.show-sql 은 jpa가 자동으로 날려주는 쿼리를 유저에게 보여주는
옵션이다.
  
+ spring.jpa.hibernate.ddl-auto는 자등으로 ddl 쿼리 생산을 허용할
것인지에 대한 코드이다.

***

순수 자바코드로는 properties.getProperty("name")과 같이 사용하는 것 같은데,
스프링에서는

    @PropertySource("classpath:./application.properties")
애노테이션을 붙여서 위치를 지정해주면 자동으로 안의 값들을 매핑해주는 것 같다.

강의에서는 SpringApplication에 별도의 애노테이션이 존재하지 않았는데,
JPA 테스트 코드에서 오류가 나서 별도로 해당 애노테이션을 명시해주니 문제없이
잘 동작하였다.
***

### YML

역할은 properties 파일이랑 같다.
다만 형식이 다르다.

    spring:
    datasource:
        password: password
        url: jdbc:h2:dev
        username: SA

이와 같이 계층적 데이터를 지정하기 편리하며, 대체로 속성파일보다
가독성이 뛰어나다.