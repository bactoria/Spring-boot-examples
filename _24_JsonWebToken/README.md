## JsonWebToken


> Header.Payload.Verify_Signature

**ex)**

> eyJ0eXAiOiJKV1QiLCJpc3N1ZURhdGUiOjE1NTE3OTYxNDY4MzUsImFsZyI6IkhTMjU2In0.eyJyb2xlIjpbIk1hbmFnZXIiLCJDdXN0b21lciJdLCJleHAiOjE1NTE3OTYyMDZ9.snmQPawLHFzbeUmV7c_M30weYs2VNtRyNU3AYdeAYtA

&nbsp;

[https://jwt.io/](https://jwt.io/) 에서 디버깅 가능

&nbsp;
&nbsp;

**HEADER**
```json
{
  "typ": "JWT",
  "issueDate": 1551796146835,
  "alg": "HS256"
}
```

&nbsp;

**PAYLOAD**
```json
{
  "role": [
    "Manager",
    "Customer"
  ],
  "exp": 1551796206
}
```

&nbsp;

**VERIFY SIGNATURE**
```json
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  SECRET_KEY
)
```

&nbsp;

### 1. Base64Url 인코딩 이유

[Base64](https://ko.wikipedia.org/wiki/%EB%B2%A0%EC%9D%B4%EC%8A%A464) : `+ / =` 이 포함되어 있음. 이 기호들은 url에서 사용 불가.

&nbsp;

### 2. 위 데이터를 변조는 불가함.

**VERIFY SIGNATURE** : header와 payload를 SHA256으로 암호화

서버에서 비밀키로 **VERIFY SIGNATURE**를 복호화하여 변조된 데이터인지 확인할 수 있음.

&nbsp;

### 3. 토큰의 내용을 숨기는 목적이 아님.

PAYLOAD의 데이터는 누구나 읽을 수가 있음. 

암호화가 아닌, 단순히 인코딩 되어있을 뿐.

따라서 보안이 필요한 데이터를 넣기에는 부적절함.

&nbsp;

https://jwt.io/ 에서도 확인 가능함.

그래서 서버에서 로그인 시에 발급을 하여 권한 정보들을 넣어서 응답하는 식인 것 같음.

&nbsp;

### 4. Trudy가 Bob 행세를 할 수 있지 않은가?

Bob의 Token 정보를 Trudy가 알고있다면

헤더에 Bob의 JWT를 추가하여 Bob 행세를 할 수 있을 것 같다.

-> Todo

&nbsp;

