```java
@RestController
public class CookieRestController {

    @GetMapping("/cookie")
    public String getCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("id", "bactoria");
        cookie.setMaxAge(60 * 5); // 5분
        cookie.setPath("/");

        response.addCookie(cookie);

        return "Did you get Cookie .";
    }
}
```

&nbsp;

### .

1. http://localhost:8080/info (접속 X - 쿠키 없음)
2. http://localhost:8080/cookie (쿠키 받기.)
3. http://localhost:8080/info (접속 O)

&nbsp;

### 쿠키 확인 (Chrome 기준)

1. http://localhost:8080/cookie
2. 개발자도구(F12) - Application - Storage - Cookie - http://localhost:8080

![image](https://user-images.githubusercontent.com/25674959/53737235-52cf9900-3ecf-11e9-8f6d-978c75e703c1.png)
