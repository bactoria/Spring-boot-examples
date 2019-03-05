package com.example._24_JsonWebToken;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtRestController {

    JwtService jwtService;

    public JwtRestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/jwt")
    public String createJwt() {
        return this.jwtService.createJwt();
    }
}
