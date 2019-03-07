[허원철님 블로그 글](https://heowc.tistory.com/46)이 정말 좋음.

2년 전 글이긴 하지만..

&nbsp;

코드도 [허원철님 깃허브](https://github.com/heowc/SpringBootSample/tree/master/SpringBootSecurityJwt) 로 공부함.


&nbsp;

postman 이용.

&nbsp;

## 로그인


&nbsp;

### request

- Method : Post
- Url : localhost:8080/login
- Content-Type="application/json"

#### Body
```json
{
    "id": "wonchul",
    "password": "1234"
}
```

&nbsp;

### response

#### Header
```
jwt-header → eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlzcyI6IndvbmNodWwiLCJpZCI6ImNvbS5leGFtcGxlLl8yNF9Kc29uV2ViVG9rZW4udXNpbmcuU2VjdXJpdHkuYXV0aC5Vc2VyRGV0YWlsc0ltcGxANWFhYzI3NTI6IFVzZXJuYW1lOiB3b25jaHVsOyBQYXNzd29yZDogW1BST1RFQ1RFRF07IEVuYWJsZWQ6IHRydWU7IEFjY291bnROb25FeHBpcmVkOiB0cnVlOyBjcmVkZW50aWFsc05vbkV4cGlyZWQ6IHRydWU7IEFjY291bnROb25Mb2NrZWQ6IHRydWU7IEdyYW50ZWQgQXV0aG9yaXRpZXM6IFVTRVIiLCJleHAiOjE1NTIwNzU3NTd9.s4w6Si1EPOWpc7JzpjFEj4BRgLJfDj6UMeXQ6l06tXo
```

&nbsp;
&nbsp;

## user 접속

### request

- Method : Get
- Url : localhost:8080/user
- Content-Type="application/json"

#### Header
```
key : jwt-header
value : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlzcyI6IndvbmNodWwiLCJpZCI6ImNvbS5leGFtcGxlLl8yNF9Kc29uV2ViVG9rZW4udXNpbmcuU2VjdXJpdHkuYXV0aC5Vc2VyRGV0YWlsc0ltcGxANWFhYzI3NTI6IFVzZXJuYW1lOiB3b25jaHVsOyBQYXNzd29yZDogW1BST1RFQ1RFRF07IEVuYWJsZWQ6IHRydWU7IEFjY291bnROb25FeHBpcmVkOiB0cnVlOyBjcmVkZW50aWFsc05vbkV4cGlyZWQ6IHRydWU7IEFjY291bnROb25Mb2NrZWQ6IHRydWU7IEdyYW50ZWQgQXV0aG9yaXRpZXM6IFVTRVIiLCJleHAiOjE1NTIwNzU3NTd9.s4w6Si1EPOWpc7JzpjFEj4BRgLJfDj6UMeXQ6l06tXo
```


&nbsp;

### response

#### Body
```
I'm Jwt Token User!
```

&nbsp;
&nbsp;

## 토큰 재발행

### request

- Method : Get
- Url : localhost:8080/token
- Content-Type="application/json"

#### Header
```
key : jwt-header
value : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlzcyI6IndvbmNodWwiLCJpZCI6ImNvbS5leGFtcGxlLl8yNF9Kc29uV2ViVG9rZW4udXNpbmcuU2VjdXJpdHkuYXV0aC5Vc2VyRGV0YWlsc0ltcGxANWFhYzI3NTI6IFVzZXJuYW1lOiB3b25jaHVsOyBQYXNzd29yZDogW1BST1RFQ1RFRF07IEVuYWJsZWQ6IHRydWU7IEFjY291bnROb25FeHBpcmVkOiB0cnVlOyBjcmVkZW50aWFsc05vbkV4cGlyZWQ6IHRydWU7IEFjY291bnROb25Mb2NrZWQ6IHRydWU7IEdyYW50ZWQgQXV0aG9yaXRpZXM6IFVTRVIiLCJleHAiOjE1NTIwNzU3NTd9.s4w6Si1EPOWpc7JzpjFEj4BRgLJfDj6UMeXQ6l06tXo
```

(유효한 토큰을 넣어야 함.)


&nbsp;
&nbsp;

iat : 토큰 발급 시간 (issued at)
nbf : 토큰 활성 시간 (not before)  
exp : 토큰 만료 시간 (expiration)  

=> 토큰은 `nbf ~ exp` 까지 유효함.