package me.bactoria;

import me.bactoria.account.Account;
import me.bactoria.account.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner__SessionFactory implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*Create Role*/
        Role role = new Role();
        role.setName("admin");

        /*Create Account*/
        Account account = new Account();
        account.setUsername("bactoria");
        account.setEmail("bactoria@gmail.com");
        account.setPassword("pass123");
        account.getRoles().add(role);


        Session session = sessionFactory.openSession();
        session.save(account);

        sessionFactory.close();


        System.out.println("finished - Neo4jRunner__SessionFactory");
    }
}
