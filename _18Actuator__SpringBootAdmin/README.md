
[spring-boot-admin](https://github.com/codecentric/spring-boot-admin)

이건 관리자 서버가 하나 더 필요로 함.

&nbsp;

`admin server` : actuator를 확인용

`admin client` : 프로덕션

&nbsp;
&nbsp;

## Admin

**pom.xml (admin)**
```xml
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.0.2</version>
    </dependency>
```
(위의 version은 spring-boot의 버전을 사용하면 됨.)

(actuator dependency도 들어가 있음.)

&nbsp;

**application.properties**
```python
server.port=18080
```

&nbsp;

#### http://localhost:18080 접속

![image](https://user-images.githubusercontent.com/25674959/53937907-e385bf00-40f1-11e9-8120-d766c53cb654.png)

&nbsp;
&nbsp;

## Client

개발중인 프로젝트에 다음과 같은 의존성 추가

**pom.xml**
```xml
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
        <version>2.0.2</version>
    </dependency>
```

(actuator dependency도 들어가 있음.)

&nbsp;

**application.properties**
```python
# admin url 추가
spring.boot.admin.client.url=http://localhost:18080

management.endpoints.web.exposure.include=*
```

&nbsp;

#### http://localhost:18080 접속

![image](https://user-images.githubusercontent.com/25674959/53937724-475bb800-40f1-11e9-81c2-e3deee613e29.png)

&nbsp;

#### (실제로 사용하려면 둘 다 Security 적용해야 함.)