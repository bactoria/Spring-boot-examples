package com.example._24_JsonWebToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    public String createJwt() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", Arrays.asList("Manager", "Customer"));

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);

        String jwtString = Jwts.builder()
//Header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setHeaderParam("alg", "HS256")
//Claims
                .setClaims(claims)
//Expiration
                .setExpiration(expireTime)
//Signature
                .signWith(SignatureAlgorithm.HS256, "myKey")
                .compact();

        return jwtString;
    }
}
