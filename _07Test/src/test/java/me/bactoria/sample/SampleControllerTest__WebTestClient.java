package me.bactoria.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
RANDOM_PORT 를 쓰면
실제로 서블릿 컨테이너(내장톰캣)이 뜬다.

이 때는 mockMVC가 아닌

TestRestTemplate나
TestWebClient를 써야한다.
*/

/*webflux를 안쓰더라도 테스트를 위해서
* webflux dependency를 추가해서 webTestClient로 테스트 하는것도 괜찮
* 편해서 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest__WebTestClient {

    /* webTestClient는 Spring5에서 추가되었고
    *  Asynchronous(비동기)적인 방법이다.
    * 요청 보내고 콜백온다.
    * Flux쪽 dependency 추가해야 한다. */
    @Autowired
    WebTestClient webTestClient;

    // 단위테스트를 위해 MockBean 추가..
    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() {
        when(mockSampleService.getName()).thenReturn("bactoria");

        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello bactoria");
    }
}