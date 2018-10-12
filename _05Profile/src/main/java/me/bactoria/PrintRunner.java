package me.bactoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PrintRunner implements ApplicationRunner {

    @Autowired
    private String hello;

    //@Value("${bactoria.name}") //value 자체는 bean 을 갖고오는게 아닌거 같음. bean은 BactoriaProperties.class 에서 처럼 @Component같은걸 해줬는데 .profile에 있는 bactoria.name 은 그냥 뭐.. 텍스트 그런데서 갖고오는거같은데 아.. 정확히 어디지?
    //private String name;

    @Autowired
    private BactoriaProperties bactoriaProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===============");
        System.out.println(hello);
        //System.out.println(name);
        System.out.println(bactoriaProperties.getName());
        System.out.println("===============");
    }
}
