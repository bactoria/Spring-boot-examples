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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void createUser__JSON() throws Exception {

        String userJson = "{\"username\" : \"junoh\", \"password\" : \"pass123\"}";

        mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8) // 응답으로 JSON을 원한다.
                    .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username",is(equalTo("junoh"))))
                .andExpect(jsonPath("$.password",is(equalTo("pass123"))))
                .andDo(print());
    }


}