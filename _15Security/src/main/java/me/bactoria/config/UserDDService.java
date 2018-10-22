package me.bactoria.config;

import me.bactoria.account.Account;
import me.bactoria.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/*UserDetailsServiceAutoConfiguration.class 에서는
*
* UserDetailsService가 빈으로 등록되있으면 자동설정을 하지 않는다.
*
* 아래 클래스는 UserDetailsService를 구현한 UserDDService 객체이며 @Configuration에 의해 빈으로 등록된다.
*
* 실행 시켜보니, 자동설정이 되지 않았다.
*
* UserDetailsServiceAutoConfiguration.class 의 자동설정 조건에는
*
* UserDetailsService.class가 빈으로 등록되어 있지 않아야 한다.
*
* Actuator로 beans를 확인해보면 UserDetailsService 라는 빈은 없다.
*
* 대신 UserDDService 라는 빈은 있다.
*
* --debug로 확인 해보았다.
*
* *** AccountService 에 있을 때 ***

*  UserDetailsServiceAutoConfiguration:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.security.authentication.AuthenticationManager,
                                                                 org.springframework.security.authentication.AuthenticationProvider,
                                                                 org.springframework.security.core.userdetails.UserDetailsService; SearchStrategy: all)
                                                                 found beans of type 'org.springframework.security.core.userdetails.UserDetailsService'
                                                                 accountService (OnBeanCondition)

* *** UserDDService 에 있을 때 ***

*UserDetailsServiceAutoConfiguration:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.security.authentication.AuthenticationManager,
                                                                 org.springframework.security.authentication.AuthenticationProvider,
                                                                 org.springframework.security.core.userdetails.UserDetailsService; SearchStrategy: all)
                                                                 found beans of type 'org.springframework.security.core.userdetails.UserDetailsService'
                                                                 userDDService (OnBeanCondition)
*
* 각 케이스의 마지막줄만 보자.. 저 클래스에서 UserDetailsService 타입의 빈을 찾았다고 한다.
* ㄷㄷㄷ...
*
*
* AutoConfiguration 의 조건에
*
* MissingBean 이 있는 경우에,
*
* 해당 빈이 없다고 해서 없는게 아니라는거....
*
*
* 이건 블로깅해야겠다.
* */

// 이거 config로 따로 빼줘도 되는건데, 이 설정 지금 AccountService.class 에 포함되어 있음.
// @Configuration // @Component 라고 해도 제대로 동작함.. @Configuration 하면 UserDDService 도 빈으로 등록됬던거같기도..
public class UserDDService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;


    /* 자.. UserDetailsService 를 빈으로 등록하는 이유는 뭐다?
    *
    * 인증 하려고... 근데 이 때 인증할 때 password가 암호화 되있어야 함.
    *
    * 안되어 있더라도 어쨋든 noOp 설정을 해서 PasswordEncoder 만들어야 함.
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 매개변수로 받은 username은 사용자가 입력했던 id임.
        //   이 Id를 DB에 username이 있는지 확인하고 난 후, 있으면 (Id, PW, 권한) 을 반환함.


        Optional<Account> byUsername = accountRepository.findByUsername(username);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
        // Optional 벗겨낸거임. accountRepository.findByUsername 결과가 없을 때 예외 처리해보리기~


        return new User(account.getUsername(), account.getPassword(), authorities());
        // account.getPassword()
        // 는 {bcrypt}$2a$10$auI7c0nB01JS6erYkLM0R.u.t1IEbkT0eSINSqixRKpFHqrO02IBy 형식으로 들어갈텐데,
        // 입력받은 비밀번호를 {} 안의 형식으로 인코딩해서 비교 하나봄...
    }

    /*User를 만들 때 GrantedAuthority를 넣어줘야함. 얘가 어떤 권한을 가지고 있는지..*/
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
