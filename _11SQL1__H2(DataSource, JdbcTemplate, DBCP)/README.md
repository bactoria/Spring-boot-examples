
### H2 콘솔 확인

**application.properties**
```
spring.h2.console.enabled=true
```

&nbsp;

#### localhost:8080/h2-console 접속

![image](https://user-images.githubusercontent.com/25674959/54530794-128c1280-49c7-11e9-8fed-ced01697b094.png)

테이블 안나오면 `JDBC URL` 확인

&nbsp;
&nbsp;
&nbsp;

## Datasource vs JdbcTemplate

### DataSource

JDBC Connection Pool 종류 3가지

1. HikariCP (Default)
2. Tomcat CP
3. Commons DBCP2

&nbsp;

`dependency에 jdbc 추가` -> `Hikari CP가 들어옴.` (Spring 2.0)

스프링 부트 2.0 부터 HikariCP 가 Default Connection Pool 이 됨.

성능이 좋다고 함.

&nbsp;

`DataSourceAutoConfiguration.class`   
    - `PooledDataSourceConfiguration` 에서  
Hikari - Tomcat - Dbcp2 순으로 import 함.

(classpath에 3개 다 있으면 HikariCP를 사용)

&nbsp;

#### Hikari CP 상세 (Default 확인 가능)

connectionTimeout: 30second   
maximumPoolSize: 10  

[Hikari CP - github](https://github.com/brettwooldridge/HikariCP#frequently-used)

동시에 일을 할 수 있는 connection은 CPU의 코어 갯수와 같다.
        connectionPool 개수를 잘 생각해야 함.

&nbsp;
&nbsp;
&nbsp;

### JdbcTemplate

스프링이 제공하는 JdbcTemplate.

DataSource보다 코드 짧음.

리소스 반납 처리 잘 되어있다.

예외 메시지 가독성 높음.

**DataSource보다 JdbcTemplate 추천.**
