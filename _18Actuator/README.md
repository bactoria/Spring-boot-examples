## Actuator

**pom.xml**
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

&nbsp;

Default : health, info 만 활성화

[엔드포인트 확인](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-endpoints-exposing-endpoints)

**application.properties**
```py
# ex 1) 모든 엔드포인트를 활성화
management.endpoints.web.exposure.include=* 

# ex 2) env, beans 만 활성화
management.endpoints.web.exposure.include=env,beans 

# ex 3) env, beans를 제외한 엔드포인트를 활성화
management.endpoints.web.exposure.include=*
management.endpoints.web.exposure.exclude=env,beans 
```

&nbsp;

**localhost:8080/actuator**

![image](https://user-images.githubusercontent.com/25674959/53930947-87617180-40d6-11e9-99b0-304ba238a325.png)

&nbsp;

Actuator를 사용할 때는 Security를 붙여서 사용할 것. (치명적인 정보 노출 방지)

&nbsp;

[spring-boot-admin](https://github.com/codecentric/spring-boot-admin) 을 이용하면 이쁜 UI 사용가능. 