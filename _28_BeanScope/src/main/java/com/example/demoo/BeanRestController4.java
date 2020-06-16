package com.example.demoo;

import com.example.demoo.beans.IBean;
import com.example.demoo.beans.MyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController
public class BeanRestController4 {

    @Autowired
    @Qualifier(value = "bb1")
    MyBean b1;

    @Autowired
    @Qualifier(value = "bb2")
    IBean b2;

    @Autowired
    @Qualifier(value = "bb3")
    IBean b3;

    @Autowired
    @Qualifier(value = "bb4")
    IBean b4;

    @GetMapping("/beans4")
    public ResponseEntity beans() {

        log.info("=======================");
        log.info("{}", b1.getClass());
        log.info("instance of MyBean is {}", b1 instanceof MyBean);
        log.info("instance of IBean is {}", b1 instanceof IBean);

        log.info("{}", b2.getClass());
        log.info("instance of MyBean is {}", b2 instanceof MyBean);
        log.info("instance of IBean is {}", b2 instanceof IBean);

        log.info("{}", b3.getClass());
        log.info("instance of MyBean is {}", b3 instanceof MyBean);
        log.info("instance of IBean is {}", b3 instanceof IBean);

        log.info("{}", b4.getClass());
        log.info("instance of MyBean is {}", b4 instanceof MyBean);
        log.info("instance of IBean is {}", b4 instanceof IBean);

        return ResponseEntity.ok().build();
    }
}
