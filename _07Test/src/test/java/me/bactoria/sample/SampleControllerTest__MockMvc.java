package me.bactoria.sample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
@SpringBootTest
== @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)

default가 "mock" 이다.
서블릿 컨테이너를 테스트용으로 띄우지 않고
Mock up 을 해서 서블릿을 Mocking을 한개 뜬다.
(내장톰캣을 사용하지 않는다.)

Mock up이 된 서블릿에 인터랙션을 하려면

MockMvc 라는 클라이언트를 사용해야 한다.
*/

/* @RunWith(SpringRunner.class)
*
* SpringRunner.class는 SpringJUnit4ClassRunner.class를 상속받음
*
* @SpringBootTest를 사용하기 위해서 꼭 붙여줘야 함.
* */

/*
* @SpringBootTest 는 빈을 모두 로드함. App 규모가 클수록 테스트가 느려짐.
* */

@RunWith(SpringRunner.class) // JUnit에 내장된 Runner 대신 SpringRunner.class를 사용할게요.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //==@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest__MockMvc {

    /*
    mockMvc 는
    Test의 WebEnvironment 가 Mock일 때 테스트할 수 있는 방법임.*/
    @Autowired
    MockMvc mockMvc;

    /*OutputCapture 를 이용하여 로그를 테스트*/
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello bactoria"))
                .andDo(print());

        /*콘솔에 찍힌 것도 테스트 가능*/
        assertThat(outputCapture.toString())
                .contains("using Logger")
                .contains("using Print");
    }
}