package me.bactoria;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component // @Component 등록 안하면 @EnableConfigurationProperties 로 추가해야함.
@ConfigurationProperties("bactoria")
@Validated //JSR-303 의 구현체
public class BactoriaProperties {

    //@NotEmpty // @Validated 를 이용한 검증. name을 비워놓고 실행해보삼~. 에러메시지 뜸. FailureAnalyzer 에 의해 친절한 에러메시지 뜸.
    @Size(min = 1, max = 200)
    private String name;

    @Min(1)
    @Max(26)
    private int age;

    private String fullName;

    @DurationUnit(ChronoUnit.SECONDS) // 니가 properties에서 정의한 것의 기본단위는 초다! (이거 안해주면 ms로 기본설정됨.) // 이거 안해주려면 properties에서 bactoria.sessionTimeout = 25s 라고 숫자 뒤에 s를 붙이면 초로 읽힘.
    private Duration sessionTimeout = Duration.ofSeconds(30); //Default는 30초로 하겠다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Duration getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

}
