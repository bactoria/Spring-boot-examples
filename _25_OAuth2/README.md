### Dependency

- Java 8
- Spring Boot 2.1.3 (Oauth2, SECURITY, WEB, JPA, H2, Lombok)

&nbsp;

## Grant Type

### 1. Authentication code Grant Type

**AuthorizationServerConfig.class**
```java
@Override
public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()

        // Client
        .withClient("bactoria")
        .secret(passwordEncoder.encode("password!"))

        .authorizedGrantTypes("authorization_code", "refresh_token")
        .redirectUris("http://localhost:18080/callback")
        .accessTokenValiditySeconds(60)
        .refreshTokenValiditySeconds(240)
        .scopes("read_profile"); // 권한
```

[인증하기](./src/main/java/me/bactoria/_25_OAuth2/httpRequest/1.%20Authentication%20Code%20Grant%20Type.http)

[Token 재발급하기](./src/main/java/me/bactoria/_25_OAuth2/httpRequest/5.%20Refresh%20Token.http)

&nbsp;
&nbsp;

### 2. Implicit Grant Type

**AuthorizationServerConfig.class**
```java
@Override
public void configure(ClientDetailsServiceConfigurer clients) throws Exception {    
    clients
        .inMemory()
            
        // Client            
        .withClient("bactoria")
        .secret(passwordEncoder.encode("password!"))

        .authorizedGrantTypes("implicit")
        .redirectUris("http://localhost:18080/callback")
        .accessTokenValiditySeconds(60)
        .scopes("read_profile"); // 권한
```

[인증하기](./src/main/java/me/bactoria/_25_OAuth2/httpRequest/2.%20Implicit%20Grant%20Type.http)

&nbsp;
&nbsp;

### 3. Resource Owner Password Credientials Grant Type

**AuthorizationServerConfig.class**
```java
@Override
public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()

        // Client
        .withClient("bactoria")
        .secret(passwordEncoder.encode("password!"))

        .authorizedGrantTypes("password", "refresh_token")
        .accessTokenValiditySeconds(60)
        .refreshTokenValiditySeconds(240)
        .scopes("read_profile"); // 권한
```

[인증하기](./src/main/java/me/bactoria/_25_OAuth2/httpRequest/3.%20Resource%20Owner%20Password%20Credentials%20Grant%20Type.http)

[Token 재발급하기](./src/main/java/me/bactoria/_25_OAuth2/httpRequest/5.%20Refresh%20Token.http)

&nbsp;
&nbsp;

### 4. Client Credentials Grant Type

**AuthorizationServerConfig.class**
```java
@Override
public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()

        // Client
        .withClient("bactoria")
        .secret(passwordEncoder.encode("password!"))

        .authorizedGrantTypes("client_credentials")
        .accessTokenValiditySeconds(60)
        .scopes("read_profile"); // 권한
```

[인증하기](./src/main/java/me/bactoria/_25_OAuth2/httpRequest/4.%20Client%20Credentials%20Grant%20Type.http)

&nbsp;
&nbsp;

### 참고

- [Github :: springboot-oauth2 (by cheese10yun)](https://github.com/cheese10yun/springboot-oauth2)
- [Spring Boot로 만드는 OAuth2 시스템 (by sbcoba)](https://brunch.co.kr/@sbcoba/1)
- [내 맘대로 OAuth 2.0 정리! (by sigmadream)](http://blog.weirdx.io/post/39955)

