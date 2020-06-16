package com.example.demoo;

import com.example.demoo.beans.IBean;
import com.example.demoo.beans.MyBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class Config {

    @Bean(name="bb1")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MyBean bb1() {
        return new MyBean();
    }

    @Bean(name="bb2")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IBean bb2() {
        return new MyBean();
    }

    @Bean(name="bb3")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyBean bb3() {
        return new MyBean();
    }

    @Bean(name="bb4")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
    public MyBean bb4() {
        return new MyBean();
    }

}
