package me.bactoria.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/*UserDetailsService를 상속받음.
*
* Question)
*
* 아놔 ... 흠...
*
* @Service가 있으니까 AccountService는 빈으로 등록 됬음..
*
* 이 때 UserDetailsService는 빈으로 등록 안되던데.. (actuator로 확인함)
*
* AccountService 안에 흠... 설정이 있는데...
*
* 이 설정이 있다는 것을 스프링 부트가 어떻게 알 수 있지?
*
*
* */

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        Account savedAccount = accountRepository.save(account);
        System.out.println("savedAccount : " + savedAccount);
        return savedAccount;
    }


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

    /*User를 만들 때 GrantedAuthority*/
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}




