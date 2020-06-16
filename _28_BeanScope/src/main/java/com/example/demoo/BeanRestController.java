package com.example.demoo;

import com.example.demoo.beans.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeanRestController {

    private final ApplicationContext applicationContext;
    private final MySingletonBean mySingletonBean;
    private MyPrototypeBean myPrototypeBean1;
    private final MyPrototypeBean myPrototypeProxyBean;
    private final MyRequestBean myRequestBean1;
    private final MySessionBean mySessionBean;


    @Autowired
    public void setMyPrototypeBean1(MyPrototypeBean myPrototypeBean1) {
        this.myPrototypeBean1 = myPrototypeBean1;
    }

    @GetMapping("/beans")
    public ResponseEntity<BeanResponseDto> beans() {

        MyPrototypeBean myPrototypeBean2 = (MyPrototypeBean) applicationContext.getBean("myPrototypeBean");
        MyRequestBean myRequestBean2 = (MyRequestBean) applicationContext.getBean("myRequestBean");
        DispatcherServlet d;
        log.info("[==================== Proxy Test =========================]");
        log.info("instanceof is {}", myPrototypeBean1 instanceof MyPrototypeBean);
        log.info("instance is {}", myPrototypeBean2 instanceof MyPrototypeBean);
        log.info("instance is {}", myPrototypeProxyBean instanceof MyPrototypeBean);

        return ResponseEntity.ok(BeanResponseDto.builder()
                .singletonBean(mySingletonBean.toString())
                .prototypeBean1(myPrototypeBean1.toString())
                .prototypeBean2(myPrototypeBean2.toString())
                .prototypeProxyBean(myPrototypeProxyBean.toString())
                .requestBean1(myRequestBean1.toString())
                .requestBean2(myRequestBean2.toString())
                .sessionBean(mySessionBean.toString())
                .build());

    }

}
