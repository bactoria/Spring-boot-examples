package me.bactoria;

import me.bactoria.config.WebSecurityConfig;
import me.bactoria.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest({HomeController.class, WebSecurityConfig.class})
// ㄷㄷㄷㄷㄷ.... WebMvcTest는 security 관련 빈을 자동으로 등록 안해주네...
// WebSecurityConfig.class 추가했음..
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void 인증하지않은_사용자가_hello_접속할경우_정상적으로_반환() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }

    @Test
    public void 인증하지않은_사용자가_my_접속할경우_리다이렉트_반환() throws Exception {
        mockMvc.perform(get("/my")
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser /*Dependency에 security test를 추가하면 MockUser를 만들 수 있음. 이걸로 인증을 대체가능.*/
    public void 정상적으로_인증한_사용자가_my_접속할경우_정상적으로_반환() throws Exception {
        mockMvc.perform(get("/my")
                //               .with(user("bactoria").authorities()) -> @WithMockUser 처럼 인증해주는 역할. 이것도 security test 필요함.
                // @WithMockUser가 눈에 더 잘 들어오는 듯...
                .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("my"));
    }
}