package me.bactoria.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://jojoldu.tistory.com/326

@Slf4j
@Configuration
public class _02_ParamsJobConfig {

    // Job 생성
    private JobBuilderFactory jobBuilderFactory;

    // Step 생성
    private StepBuilderFactory stepBuilderFactory;

    public _02_ParamsJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    // step1 -> END
    @Bean
    public Job paramsJob() {
        return jobBuilderFactory.get("paramsJob")
                .start(paramsStep1(null)) // Step1 실행
                .build();
    }

    @Bean
    @JobScope
    public Step paramsStep1(@Value("#{jobParameters[requestDate]}") String requestDate) { // Add tasklet  or  RPW (read,process,write) in Step
        return stepBuilderFactory.get("paramsStep1")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("########  paramsStep1  ########  ");
                    log.info("requestDate : ", requestDate);

                    //throw new IllegalAccessException();
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
