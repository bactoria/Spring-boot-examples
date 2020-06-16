package com.example.demoo;

import lombok.*;

@Getter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class BeanResponseDto {
    private String singletonBean;
    private String prototypeBean1;
    private String prototypeBean2;
    private String prototypeProxyBean;
    private String requestBean1;
    private String requestBean2;
    private String sessionBean;
}
