package me.bactoria.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;



/*
* @SpringBootTest 로 테스트를 하면 모든 빈등록이 이루어짐.
* application.properties 도 읽으니까..
* 이건 h2 DB로 테스트가 아니라 postgresql DB로 테스트함.
* 절대 권장하지 않음.
*
* 왜냐?
*
* 1. 느리다.
*
* 2. 테스트하려면 테스트용으로 별도의 postgreSQL 만들어야 함.
* 만들어서 test용 application.properties 에 dataSource 설정 파일들 변경해주어야 함.( properties로 줘도 되고..) 암튼복잡해.
*
* */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest_SpringBootTest {

    //  @DataJpaTest 추가하면 아래의 빈들도 추가해줌.
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void test001() throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();

            //PostgreSQL 왜냐하면 @SpringBootTest 때문.
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }


    }
}