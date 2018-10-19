package me.bactoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DBRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    private final Logger logger = LoggerFactory.getLogger(DBRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info(dataSource.getConnection().getMetaData().getDriverName());
    }
}
