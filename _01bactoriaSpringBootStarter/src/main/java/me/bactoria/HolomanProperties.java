package me.bactoria;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("holoman") //prefix: holoman
public class HolomanProperties {

    private String name = "This is Default properties"; // @Bean 추가 안하고, properties도 추가 안했을 경우에 이 값을 사용. 한마디로 AutoConfiguration일 경우에 사용하는 값
    private int HowLong = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowLong() {
        return HowLong;
    }

    public void setHowLong(int howLong) {
        HowLong = howLong;
    }
}
