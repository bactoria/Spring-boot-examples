package me.bactoria;

import me.bactoria.account.Account;
import me.bactoria.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Application {

    /*방법 1. (Repository 안만들어도 됨.)*/
    @Autowired
    MongoTemplate mongoTemplate;

    /*방법 2. (Repository 만들어야 함.)*/
    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {

            /* Using MongoTemplate */
            Account account1 = new Account();
            account1.setUsername("mongoTemplate");
            account1.setPassword("pass123");

            mongoTemplate.insert(account1);


            /*Using AccountRepository*/
            Account account2 = new Account();
            account2.setUsername("mongoRepository");
            account2.setPassword("pass123");

            accountRepository.save(account2);

            System.out.println("finished");

            /* CLI command
            db
            using test
            db.accounts.find({})
            * */
        };
    }
}
