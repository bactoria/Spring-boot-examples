package me.bactoria;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("(Runner) " + args.getOptionNames());
        System.out.println("(Runner) " + args.containsOption("debug"));
        System.out.println("(Runner) " + args.containsOption("debu"));
    }
}
