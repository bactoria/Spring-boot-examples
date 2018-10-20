package me.bactoria;

import me.bactoria.account.Account;
import me.bactoria.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RedisHashRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account();
        account.setUsername("bactoria");
        account.setPassword("pass123");

        System.out.println("Befor save : " + account.getId()); //null

        accountRepository.save(account);

        System.out.println("After save : " + account.getId()); // ex) 3cd3013d-84bd-437a-b63d-7b4dc94e5623
        // save하니까 account에 id가 생김..ㄷㄷ 신기

        // Account의 setId 메소드가 없다. 근데 account.getId가 가능하다고?
         System.out.println(accountRepository.findById(account.getId()).get().getUsername());
        System.out.println(accountRepository.findById(account.getId()).get().getPassword());

 /* redis cli 명령어

  keys *
  해쉬달린것들은 hget을 써야함.
  hgetall accounts:c07e064a-03bc-4c00-bf9f-ca9db7b3b802
  hget: accounts:c07e064a-03bc-4c00-bf9f-ca9db7b3b802 username

 * */

    }
}
