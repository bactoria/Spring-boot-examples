package me.bactoria.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest__Accept__XML {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createUser__XML() throws Exception {

        String userJson = "{\"username\" : \"junoh\", \"password\" : \"pass123\"}";

        mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_XML) // 응답으로 XML을 원한다.
                    .content(userJson))
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("junoh"))
                .andExpect(xpath("/User/password").string("pass123"))
                .andDo(print());

/*
        JacksonHttpMessageConvertersConfiguration.class

        를 보면 xml로 변환시키기 위해서는

        @ConditionalOnClass(XmlMapper.class)이다.

        XmlMapper.class가 classpath 에 있을 때에 사용 가능하다.

        classpath에 넣는 법은

        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
            <version>2.9.6</version>
        </dependency>

        pom.xml에 추가
*/

    }
}