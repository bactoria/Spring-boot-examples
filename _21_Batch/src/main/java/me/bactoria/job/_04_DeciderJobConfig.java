package me.bactoria.job;

//https://jojoldu.tistory.com/328

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

//https://jojoldu.tistory.com/328

/*
 * JobExecutionDecider 클래스를 빈으로 등록함.
 *
 *분기 로직이 step 과 분리되어 있음. -> Good
 *
 * 상태반환은 FlowExecutionStatus("아무고나~") 원하는대로 만들 수 있음
 *
 * 분기 로직 : OddDecider
 * */

@Slf4j
@Configuration
public class _04_DeciderJobConfig {

    JobBuilderFactory jobBuilderFactory;
    StepBuilderFactory stepBuilderFactory;

    public _04_DeciderJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job deciderJob() {
        return this.jobBuilderFactory.get("deciderJob")
                .start(startStep())

                .next(decider())
                .from(decider()) // decider의 상태가
                    .on("ODD") // ODD라면
                    .to(oddStep()) // oddStep로 간다.
                .from(decider()) // decider의 상태가
                    .on("EVEN") // EVEN 이라면
                    .to(evenStep()) // evenStep로 간다.

                .end() // builder 종료
                .build();

    }

    @Bean
    public Step startStep() {
        return this.stepBuilderFactory.get("startStep")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>>>> Start!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> 홀수입니다.");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step evenStep() {
        return stepBuilderFactory.get("evenStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> 짝수입니다.");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new OddDecider();
    }

    public static class OddDecider implements JobExecutionDecider {

        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            Random rand = new Random();

            int randomNumber = rand.nextInt(50) + 1;
            log.info("랜덤숫자: {}", randomNumber);

            if (randomNumber % 2 == 0) {
                return new FlowExecutionStatus("EVEN");
            } else {
                return new FlowExecutionStatus("ODD");
            }
        }
    }


}
