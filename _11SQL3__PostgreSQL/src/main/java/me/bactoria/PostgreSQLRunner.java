package me.bactoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgreSQLRunner implements ApplicationRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(PostgreSQLRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info(jdbcTemplate.getDataSource().toString());
        logger.info(jdbcTemplate.getDataSource().getConnection().getMetaData().getDriverName());
        logger.info(jdbcTemplate.getDataSource().getConnection().getMetaData().getURL());
        logger.info(jdbcTemplate.getDataSource().getConnection().getMetaData().getUserName());

        jdbcTemplate.execute("CREATE TABLE USERS (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))");
        jdbcTemplate.execute(" INSERT INTO USERS VALUES (1, 'keesun')");
    }
}
