package me.bactoria;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* 아래의 @CrossOrigin 은 모두 유효하다.
* 하지만 따로 config 파일을 만들어서 할게
* */

//@CrossOrigin //컨트롤러 통틀어서 각 메소드 일괄적으로 적용됨
//@CrossOrigin(origins = "http://localhost:18080")
@RestController
public class SampleController {

    //@CrossOrigin(origins = "http://localhost:18080")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
