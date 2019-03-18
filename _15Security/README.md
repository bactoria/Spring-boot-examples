### 시큐리티에 H2 콘솔 사용

**application.properties**
```
spring.h2.console.enabled=true
```

&nbsp;

**security configure**
```
http.csrf().disable()
    .authorizeRequests().antMatchers("/h2-console/*").permitAll().and()
    .headers().frameOptions().disable();
```

&nbsp;
&nbsp;

추가할 내용이 많다. 

어디서 부터 시작해야할지 모르겠다.

블로깅하는게 나을거 같기도..