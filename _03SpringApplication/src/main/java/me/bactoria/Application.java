package me.bactoria;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/* args 추가하는 법

Ctrl+Shift+A
=> Edit Configurations...
=> Configuration
=> Environment
=> Program arguments
=> args 추가 ( ex) --debug )

*/

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        // SpringApplication.run(application.class, args);

        // static으로 실행한 run은 커스텀할 방법이 없음.. 그래서 Builder를 쓰거나 객체 생성해서 run 시킴.
        new SpringApplicationBuilder(Application.class)

                //배너 코딩으로 만들 수 있음. 근데 이거 resources/banner.txt에 짐ㅋㅋㅋ
                .banner((environment, sourceClass, out) -> {
                    out.println("================");
                    out.println(" 코딩으로 만든 배너 ");
                    out.println("================");
                })

                // StartingListener 는 ApplicationContext가 생성되기 전에 실행하므로 bean으로 등록할 수 없다.
                //따라서 아래처럼 객체 만들어서 등록해주어야 함. (StartedListener의 경우에는 ApplicationContext 생성 이후에 실행되므로 @Component를 이용하여 bean 등록함.)
                .listeners(new StartingListener())

                .run(args);
    }
}
