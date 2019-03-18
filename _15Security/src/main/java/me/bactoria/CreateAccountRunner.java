package me.bactoria;

import me.bactoria.account.Account;
import me.bactoria.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("user1");
        account.setPassword(passwordEncoder.encode("pass")); //패스워드 암호화해서 저장.
        // => {bcrypt}$2a$10$auI7c0nB01JS6erYkLM0R.u.t1IEbkT0eSINSqixRKpFHqrO02IBy 이런식으로 변형되어서 DB에 저장됨.

        accountService.createAccount(account);


        account = new Account();
        account.setUsername("user2");
        account.setPassword(passwordEncoder.encode("pass"));

        accountService.createAccount(account);


        System.out.println("Runner is finished");
    }
}
