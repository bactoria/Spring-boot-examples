package me.bactoria._25_OAuth2.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello~";
    }

    @GetMapping("/my")
    public String hello(Principal principal) {
        return principal.toString();
    }

}
