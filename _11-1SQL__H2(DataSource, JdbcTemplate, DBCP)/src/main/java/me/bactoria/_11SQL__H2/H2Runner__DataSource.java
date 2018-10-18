package me.bactoria._11SQL__H2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

// DataSource vs JdbcTemplate
@Component
public class H2Runner__DataSource implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(H2Runner__DataSource.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*
        dependency에 jdbc를 추가하면 Hikari 가 들어옴.
        DBCP 중 하나임.
        스프링부트는 Hikari DBCP를 Default로 사용함.

        -> DataSourceAutoConfiguration.java 에서
        PooledDataSourceConfiguration를 보면
        Hikari - Tomcat - Dbcp2 순서로 import 하는 것을 볼 수 있음.

        =>  classpath에 HikariCP, Tomcat CP, Commons DBCP2
         3개가 다 있으면 HikariCP를 사용함.

        Hikari CP 상세 (Default 확인 가능) - connectionTimeout: 30second / maximumPoolSize: 10
        https://github.com/brettwooldridge/HikariCP#frequently-used
        */

        /*
        동시에 일을 할 수 있는 connection은 CPU의 코어 갯수와 같다.
        connectionPool 개수를 잘 생각해야 함.
        */

        try (Connection connection = dataSource.getConnection()) {

            // connection 정보 찍기
            logger.info(dataSource.toString());
            logger.info(connection.getMetaData().getURL());
            logger.info(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER_DS (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }
    }
}
