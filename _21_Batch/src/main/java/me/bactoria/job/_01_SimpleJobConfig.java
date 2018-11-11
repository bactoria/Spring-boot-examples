package me.bactoria.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://jojoldu.tistory.com/325
// 가장 Basic한 Batch 데모

@Slf4j
@Configuration
public class _01_SimpleJobConfig {

    // Job 생성
    private JobBuilderFactory jobBuilderFactory;

    // Step 생성
    private StepBuilderFactory stepBuilderFactory;

    public _01_SimpleJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    // step1 -> step2 -> END
    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1()) // Step1 실행
                .next(simpleStep2()) // Step2 실행
                .build();
    }

    @Bean
    public Step simpleStep1() { // Add tasklet  or  RPW (read,process,write) in Step
        return stepBuilderFactory.get("simpleStep1")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("########  paramsStep1  ########  ");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step simpleStep2() { // Add tasklet  or  RPW (read,process,write) in Step
        return stepBuilderFactory.get("simpleStep2")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("########  paramsStep2  ########  ");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

}
