package com.example._24_JsonWebToken.using.Security.auth.jwt;

import com.example._24_JsonWebToken.using.Security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("Token is not exist!");
		}

		String token = authentication.getCredentials().toString();

		if (JwtUtil.verify(token)) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(token);
			return new JwtAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		} else {
			throw new BadCredentialsException("This token is not verified.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
