package me.bactoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/*
 * 이거 실행시키려면
 * 먼저, bactoriaSpringBootStarter 를 npm install 해서
 * 로컬 maven 저장소에 install 시켜주어야 함.
 */

/*
 * Runner 추가.
 * holoman이 빈으로 잘 등록되었는지 확인.
 * */

// AutoConfiguration 만들기
@Component
public class HolomanRunner implements ApplicationRunner {

    @Autowired
    private Holoman holoman;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoman);
    }
}
