package com.example._24_JsonWebToken.using.Security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example._24_JsonWebToken.using.Security.auth.jwt.JwtInfo;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class JwtUtil {

    public static String createToken(UserDetails userDetails) {
        return createToken(userDetails, DateUtil.nowAfterMinutesToDate(JwtInfo.EXPIRES_LIMIT), DateUtil.nowAfterMinutesToDate(JwtInfo.NOT_BEFORE));
    }

    private static String createToken(UserDetails userDetails, Date expDate, Date nbf) {
        try {
            return JWT.create()
                    .withIssuer(JwtInfo.ISSUER)
                    .withClaim("id", userDetails.getUsername())
                    .withClaim("role", userDetails.getAuthorities().toArray()[0].toString())
                    .withIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(9))))
                    .withNotBefore(nbf)
                    .withExpiresAt(expDate)
                    .sign(JwtInfo.getAlgorithm());
        } catch (JWTCreationException createEx) {
            return null;
        }
    }

    public static Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(JwtInfo.getAlgorithm()).build();
            verifier.verify(token);

            return Boolean.TRUE;
        } catch (JWTVerificationException verifyEx) {
            return Boolean.FALSE;
        }
    }

    public static String refreshToken(UserDetails userDetails) {
        return createToken(userDetails, DateUtil.nowAfterMinutesToDate(JwtInfo.EXPIRES_LIMIT), DateUtil.nowAfterMinutesToDate(JwtInfo.NOT_BEFORE));
    }

    public static DecodedJWT tokenToJwt(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException decodeEx) {
            return null;
        }
    }
}
