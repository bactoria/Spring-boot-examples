package com.example.demoo;

import com.example.demoo.beans.IBean;
import com.example.demoo.beans.MyPrototypeBean;
import com.example.demoo.beans.MySingletonBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController
@RequiredArgsConstructor
public class BeanRestController2 {

    private final ApplicationContext applicationContext;
    private final MySingletonBean mySingletonBean;
    private final MyPrototypeBean myPrototypeBean1;
    private final IBean myRequestBean;

    @GetMapping("/beans2")
    public ResponseEntity<BeanResponseDto> beans() {

        MyPrototypeBean myPrototypeBean2 = (MyPrototypeBean) applicationContext.getBean("myPrototypeBean");

        return ResponseEntity.ok(BeanResponseDto.builder()
                .singletonBean(mySingletonBean.toString())
                .singletonBean(mySingletonBean.toString())
                .prototypeBean1(myPrototypeBean1.toString())
                .prototypeBean2(myPrototypeBean2.toString())
                .requestBean1(myRequestBean.toString())
                .build());

    }

}
