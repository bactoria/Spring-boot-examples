package com.example.demoo;

import com.example.demoo.beans.MySessionBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SessionController {

    private final MySessionBean mySessionBean;

    // 여기 쿠키헤더에 JSESSIONID 없이 요청하면 Response로 Set-Cookie: JSESSIONID=41~~~; PATH=/;  HttpOnly 를 줌. 신기하군.
    @GetMapping("/scope/session")
    public ResponseEntity beans(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        System.out.println(request);
        System.out.println(request);
        System.out.println(request.getCookies());

        Cookie[] cookies = Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]);

        System.out.println(response.getHeader("Set-Cookie")); // 없음
        String result = mySessionBean.toString();
        System.out.println(response.getHeader("Set-Cookie")); // 있음

        String sessionId = Arrays.stream(cookies)
                .filter(c -> c != null && c.getName().equals("JSESSIONID"))
                .findFirst()
                .map(c -> c.getValue())
                .orElseGet(() -> response.getHeader("Set-Cookie"));

        return ResponseEntity.ok(result +"\r\n"+ "sessionId: " + sessionId);
    }

    @GetMapping("/scope/session/not")
    public ResponseEntity beansNot(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok("쿠키없음");
    }
}
