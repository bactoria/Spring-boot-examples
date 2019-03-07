package com.example._24_JsonWebToken.using.Security.auth.jwt;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtInfo {

	public static final String HEADER_NAME = "jwt-header";

	public static final String ISSUER = "wonchul";

	public static final String TOKEN_KEY = "heowc.github.io";

	public static final long EXPIRES_LIMIT = 5L;

	public static final long NOT_BEFORE = 0L;

	public static Algorithm getAlgorithm() {
		try {
			return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
		} catch (IllegalArgumentException e) {
			return Algorithm.none();
		}
	}
}
