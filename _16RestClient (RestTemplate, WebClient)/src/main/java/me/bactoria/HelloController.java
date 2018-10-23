package me.bactoria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(3000);
        return "hello";
    }

    @GetMapping("/world")
    public String world() throws InterruptedException {
        Thread.sleep(5000);
        return "world";
    }

    @GetMapping("/bye")
    public String bye() throws InterruptedException {
        Thread.sleep(6000);
        return "bye";
    }

}
