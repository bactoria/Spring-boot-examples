package me.bactoria;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {

    @Bean
    @ConditionalOnMissingBean // 덮어쓰기 방지. Bean이 만들어져있지 않다면 (@ComponentScan 으로 인해 미리 추가된 경우 Bean이 만들어져 있음) 추가하겠다. - 덮어씌우는 것을 방지 (이거없으면 빈설정 해줘도 무조건 AutoConfiguration 에서 설정한 거로 적용됨.)
    public Holoman holoman(HolomanProperties properties) {
        Holoman holoman = new Holoman();

        holoman.setName(properties.getName());
        holoman.setHowLong(properties.getHowLong());

        return holoman;
    }
}
