package me.bactoria.thymeleaf.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String getHello(Model model) {
        model.addAttribute("name","bactoria");
        return "hello";
    }
}
