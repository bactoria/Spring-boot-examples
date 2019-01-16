package me.bactoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    // @Autowired
    // RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("name","bactoria"); // == set name bactoria
        values.set("springboot","2.0"); // == set springboot 2.0
        values.set("hello","world"); // == set hello world

    }
}
