package com.example._23_cookie;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/info")
    public String info(@CookieValue(value = "id", required = true) Cookie storeIdCookie) {
        return "You Have Cookie ! " + " name : " + storeIdCookie.getName() + "  value : " + storeIdCookie.getValue();
    }

}
