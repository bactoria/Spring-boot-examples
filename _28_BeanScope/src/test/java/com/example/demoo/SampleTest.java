package com.example.demoo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleTest {

  //  @Autowired
    ApplicationContext applicationContext;

//    @Autowired
    BeanRestController4 beanRestController4;

//    @Test
    public void asd() {
        assertThat(beanRestController4).isNotNull();
    }
}
