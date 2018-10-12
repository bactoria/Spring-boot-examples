package me.bactoria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/* Test 용 property가 필요한 경우가 있을 것임!  예) Rest Api 에서 Security id,pw를 테스트용으로 만들 때
*  이에 여러가지 방법들이 있음.
*  1. @TestPropertySource(properties = "")
*  2. @SpringBootTest(properties = "")
*  3. @TestPropertySource(locations = "")
*  4. test/resources/application.properties
* */


@RunWith(SpringRunner.class)

// 백기선님한테 한 질문
// https://www.inflearn.com/dwquestion/testpropertysource-springboottest-%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84-%EA%B4%80%EB%A0%A8-%EC%A7%88%EB%AC%B8%EB%93%9C%EB%A6%BD%EB%8B%88%EB%8B%A4/


//@TestPropertySource(properties = "from.route=@TestPropertySource")
@TestPropertySource(locations = "classpath:/ApplicationTest.properties")

//@SpringBootTest(locations = "classpath:/ApplicationTest.properties") // 사용불가. locations는 @TestPropertySoure를 이용할것.
//@SpringBootTest(properties = "{from.route=@SpringBootTest, ~~, ~~}") // 여러 property 사용가능
@SpringBootTest(properties = "from.route=@SpringBootTest")
public class ApplicationTest {

    @Autowired
    private Environment environment;

    @Test
    public void testProperties() {

        assertThat(environment.getProperty("from.route"))
                .isEqualTo("@TestPropertySource");

    }
}
