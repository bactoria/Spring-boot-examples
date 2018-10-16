package me.bactoria.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
RANDOM_PORT 를 쓰면
실제로 서블릿 컨테이너(내장톰캣)이 뜬다.

이 때는 mockMVC가 아닌

TestRestTemplate나
TestWebClient를 써야한다.
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest__TestRestTemplate {

    @Autowired
    TestRestTemplate testRestTemplate;

    // 단위테스트를 위해 MockBean 추가..
    @MockBean
    SampleService mockSampleService;


    @Test
    public void hello() {
        when(mockSampleService.getName()).thenReturn("bactoria");

        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello bactoria");
    }
}