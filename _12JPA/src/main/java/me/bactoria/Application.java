package me.bactoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*
* ORM ? - Object-Relational Mapping

객체와 관계를 매핑

* 어떤 한 클래스는 여러가지 프로퍼티들을 가질 수 있다.
* 테이블은 테이블과 컬럼밖에 없다.
*
* 객체는 크기가 되게 다양한데
* 테이블은 크기가 한정적임.
*
* 이 객체의 다양함을 테이블에 어떻게 맵핑을 시켜야 하는가?
*
* 클래스간의 상속.. 테이블은 상속이 없다.
*

    PK
*
* 물론 여러개의 필드를 조합해서 만든 pk도 있고...
*
* Object에서는 identity는 무엇?.
* 객체의 해쉬코드?

* 또는 equals Method ?
*
* 그러면은 Object identity가 같으면...
*
* 도대체 어떤 엔티티가 같아야 우린 객체가 같다고 해야 할까?
*
*  컬럼에서는 id만 같으면 같은건데..
*
*  Object에서는 음.. id가 null이면 어떡하지??
*


*
*  JPA 스펙이 hibernate에 기반해서 만들어짐.
*
*  hibernate의 모든 기능을 커버하진 않음.
* */

/* spring-data-jpa는
* JPA 표준 스펙은 쉽게 사용할 수 있게끔
* Spring data로 추상화 시켜놓은 것.
* */

// spring-data-jpa -> Jpa -> Hibernate -> DataSource 이렇게 내려감.
// 우리는 Spring Data Jpa 사용.

// jpa 의존성 보면 jdbc 있음.
// jpa dependency 추가하면
// jdbcTemplate 와 DataSource 도 빈으로 등록됨.
// spring-data-jpa 도 들어오고,
// hibernate도 들어온다.
// spring-data-jpa 를 보면 orm이 들어오고...
