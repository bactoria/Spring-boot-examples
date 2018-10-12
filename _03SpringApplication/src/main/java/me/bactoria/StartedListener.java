package me.bactoria;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component // 리스너 빈등록.  .listeners(new StartingListener()) 로 대체 가능함.
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("==============");
        System.out.println("  Started");
        System.out.println("==============");
    }
}
