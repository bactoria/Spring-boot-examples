package com.example._24_JsonWebToken.using.Security.auth.ajax.filter;

import com.example._24_JsonWebToken.using.Security.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public AjaxAuthenticationFilter(RequestMatcher requestMatcher, ObjectMapper objectMapper) {
        super(requestMatcher);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!isJson(request)) throw new AccessDeniedException("Don't use content type for " + request.getContentType());

        Member member = objectMapper.readValue(request.getReader(), Member.class);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
        return getAuthenticationManager().authenticate(authentication);
    }

    private boolean isJson(HttpServletRequest request) {
        return MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType());
    }
}
