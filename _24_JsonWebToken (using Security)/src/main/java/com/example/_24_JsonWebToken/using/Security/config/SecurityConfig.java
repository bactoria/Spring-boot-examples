package com.example._24_JsonWebToken.using.Security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example._24_JsonWebToken.using.Security.auth.BaseSecurityHandler;
import com.example._24_JsonWebToken.using.Security.auth.ajax.AjaxAuthenticationProvider;
import com.example._24_JsonWebToken.using.Security.auth.ajax.AjaxUserDetailsService;
import com.example._24_JsonWebToken.using.Security.auth.ajax.filter.AjaxAuthenticationFilter;
import com.example._24_JsonWebToken.using.Security.auth.jwt.JwtAuthenticationProvider;
import com.example._24_JsonWebToken.using.Security.auth.jwt.JwtUserDetailsService;
import com.example._24_JsonWebToken.using.Security.auth.jwt.filter.JwtAuthenticationFilter;
import com.example._24_JsonWebToken.using.Security.auth.jwt.matcher.SkipPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationProvider jwtProvider;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final AjaxAuthenticationProvider ajaxProvider;
	private final AjaxUserDetailsService ajaxUserDetailsService;
	private final BaseSecurityHandler securityHandler;
	private final ObjectMapper objectMapper;
	private final PasswordEncoder passwordEncoder;

	private static final String LOGIN_ENTRY_POINT = "/login";
	private static final String TOKEN_ENTRY_POINT = "/token";
	private static final String ERROR_ENTRY_POINT = "/error";
	private static final String ROOT_ENTRY_POINT = "/**";

	@Autowired
	public SecurityConfig(JwtAuthenticationProvider jwtProvider, JwtUserDetailsService jwtUserDetailsService, AjaxAuthenticationProvider ajaxProvider, AjaxUserDetailsService ajaxUserDetailsService, BaseSecurityHandler securityHandler, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
		this.jwtProvider = jwtProvider;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.ajaxProvider = ajaxProvider;
		this.ajaxUserDetailsService = ajaxUserDetailsService;
		this.securityHandler = securityHandler;
		this.objectMapper = objectMapper;
		this.passwordEncoder = passwordEncoder;
	}

	// Resource 자원 http로 접근하지 못하도록 막기
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(ajaxProvider)
				.authenticationProvider(jwtProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtAuthenticationFilter(), FilterSecurityInterceptor.class)
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(ROOT_ENTRY_POINT).authenticated()
				.antMatchers(TOKEN_ENTRY_POINT).permitAll()
				.antMatchers(LOGIN_ENTRY_POINT).permitAll()
				.antMatchers(ERROR_ENTRY_POINT).permitAll();
	}

	@Bean
	public AntPathRequestMatcher antPathRequestMatcher() {
		return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
	}

	@Bean
	public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
		AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(securityHandler);
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}

	@Bean
	public SkipPathRequestMatcher skipPathRequestMatcher() {
		return new SkipPathRequestMatcher(Arrays.asList(LOGIN_ENTRY_POINT, TOKEN_ENTRY_POINT, ERROR_ENTRY_POINT));
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}
}