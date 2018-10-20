package me.bactoria.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/* Mongo DB는 테스트용 내장 Mongo DB가 있음.
*  프로덕션 DB에 아무 영향을 안줌.
* 디펜던시 추가해줘야 함.
* */

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findByUsername() {

        final String USERNAME = "test001";

        Account account = new Account();
        account.setUsername(USERNAME);
        account.setPassword("pass001");

        accountRepository.save(account);

        Optional<Account> user = accountRepository.findByUsername(USERNAME);

        assertThat(user).isNotEmpty();
        assertThat(user.get().getUsername()).isEqualTo(USERNAME);
    }

}