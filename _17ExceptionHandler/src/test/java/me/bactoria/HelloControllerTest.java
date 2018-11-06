package me.bactoria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    //@Test(expected = HelloNotFoundException.class) <- 핸들어에 의해 처리되기 때문에 이렇게 하면 Test fail 뜬다.
    @Test
    public void 존재하지않는_hello_요청시_핸들러에의해_NotFound() throws Exception {

        //when
        mockMvc.perform(get("/hello/1"))
                //then
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is("AC_001")))
                .andExpect(jsonPath("$.message", is("해당 Hello를 찾을 수 없습니다.")))
                .andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    public void world요청시_핸들러에의해_BadRequest() throws Exception {

        //when
        mockMvc.perform(get("/world"))
                //then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("AC_002")))
                .andExpect(jsonPath("$.message", is("WORLD 워어얼~드")))
                .andExpect(jsonPath("$.status", is(400)));
    }

}