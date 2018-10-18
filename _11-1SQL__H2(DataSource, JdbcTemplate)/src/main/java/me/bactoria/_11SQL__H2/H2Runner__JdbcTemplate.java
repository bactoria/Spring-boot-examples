package me.bactoria._11SQL__H2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// DataSource vs JdbcTemplate

/*
스프링이 제공하는 JdbcTemplate.

JdbcTemplate가 코드 더 간결함.
리소스 반납 처리 잘 되어있다.
예외 메시지 가독성 높음.
DataSource보다는 JdbcTemplate 추천.
*/
@Component
public class H2Runner__JdbcTemplate implements ApplicationRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(H2Runner__JdbcTemplate.class);

    @Override
    public void run(ApplicationArguments args) throws SQLException {

        // connection 정보 찍기
        logger.info(jdbcTemplate.getDataSource().getConnection().getMetaData().getURL());
        logger.info(jdbcTemplate.getDataSource().getConnection().getMetaData().getUserName());

        jdbcTemplate.execute("CREATE TABLE USER_JT(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))");
    }
}
