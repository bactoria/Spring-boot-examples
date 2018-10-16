package me.bactoria.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* Slice Test
 *
 * @SpringBootTest 대신
 * @WebMvcTest 를 쓰면 좋은점.
 *
 * @SpringBootTest를 쓰면 bean등록을 모두 한다.
 *
 * @WebMvcTest를 쓰면

 * @Controller, @ControllerAdvice, @JsonComponent,
 * Converter, GenericConverter, Filter,
* WebMvcConfigurer, HandlerMethodArgumentResolver
* 만 빈등록됨.
*
* @Component 는 빈등록 안됨.
*/

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest__MockMvc2 {

    @Autowired
    MockMvc mockMvc;

    /*@Service는 빈등록 안됨.
    * 그래서 @MockBean으로 주입해줘야 함.
    * @Test 할 때마다 자동으로 리셋됨.
    * */
    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("bactoria");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello bactoria"))
                .andDo(print());
    }
}