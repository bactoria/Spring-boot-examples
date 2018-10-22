package me.bactoria.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/* Security A to Z (까진 아니고,,,)
*
* 음... spring-boot-starter-security를 의존성에 추가하면
*
* security를 autoConfiguration 해주겠지?
*
* autoConfiguration해주는 것들 클래스가 autoConfiguration으로 끝나니까...
*
* SecurityAutoConfiguration.class 가 있겠고.. Shift 두번 클릭해서 검색해서 들어가보면...
*
* authenticationEventPublisher 를 빈등록 하는 것 뿐만 아니라.. ( authenticationEventPublisher 는 이벤트 발생시킴. 리스너 등록 가능 함.)
*
*
*
*@Import를 보면...SpringBootWebSecurityConfiguration.class, WebSecurityEnablerConfiguration.class,
		SecurityDataConfiguration.class 를 임포트함.

* 다 configuration 하는 것들인데...
*
* SpringBootWebSecurityConfiguration.class 를 보자
*
* 발동조건은 WebSecurityConfigurerAdapter.class 가 있는데 빈으로는 안 만들어져있을 때, (내가 안만들었을 때)
*
* WebSecurityConfigurerAdapter 를 자기가 상속받아서 쓰네. override 한거 하나도없음. 그냥 WebSecurityConfigurerAdapter설정 그대로 가져다 씀.
*
* WebSecurityConfigurerAdapter 의 getHttp() 보면 밑에 configure(http); 로 http 설정하는 부분이 있음.
*
* 아래의 소스들은 이 configure를 오버라이드 한 것임.
*
* 자.. 이렇게 오버라이드하고 @Component로 빈등록 하면..?
*
* SpringBootWebSecurityConfiguration 은 자동설정 안할 것임.
*
* --debug 찍어서 확인해보면 Did not match 확인 가능함.
*
* */

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/my").authenticated()
                .anyRequest().permitAll()
                .and().formLogin() // request의 accept 헤더가 http라서 여기서 걸림. 폼로그인 할 수 있게 폼창을 줌.
                .and().httpBasic(); //
    }
}