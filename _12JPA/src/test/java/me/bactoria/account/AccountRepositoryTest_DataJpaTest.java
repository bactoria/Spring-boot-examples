package me.bactoria.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// DataJpaTest 할 때는 인메모리 DB가 반드시 필요하다. 따라서 의존성에 h2를 추가해주자.
// Test는 h2를 사용.
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest_DataJpaTest {

    //  @DataJpaTest 추가하면 아래의 빈들도 추가해줌.
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void test001() {
        /*
        빈거 돌리면 다음과 같은 것을 볼 수 있음.
        Hibernate: drop table account if exists
        Hibernate: drop sequence if exists hibernate_sequence
        Hibernate: create sequence hibernate_sequence start with 1 increment by 1
        Hibernate: create table account (id bigint not null, password varchar(255), username varchar(255), primary key (id))
        빈거 돌렸는데 테이블 생성했음.
*/
    }

    @Test
    public void test002() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            //H2 왜냐하면 @DataJpaTest 때문.
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }
    }

    @Test
    public void test003() {

        Account account = new Account();
        account.setUsername("bactoria");
        account.setPassword("pass123");

        Account newAccount = accountRepository.save(account);
        assertThat(newAccount).isNotNull();

        Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        assertThat(existingAccount).isNotNull();

        Optional<Account> nonExistingAccount = accountRepository.findByUsername("nothing");
        assertThat(nonExistingAccount).isEmpty();

//      Account nonExistingAccount = accountRepository.findByUsername("nothing");
//      assertThat(nonExistingAccount).isNull();

    }

}