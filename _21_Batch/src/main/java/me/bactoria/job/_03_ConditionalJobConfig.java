package me.bactoria.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//https://jojoldu.tistory.com/328

/*
* 분기 로직이 step 에서 이루어짐. -> Bad
*
* step에서 상태반환은 ExitStatus로 반환하는데.. 문제가 있다.
* ExitStatus는 종류가 정해져 있고, (UNKNOWN, EXECUTING, COMPLETED, NOOP, FAILED, STOPPED)
* 커스텀하기 위해서는 별도의 리스너를 만들어 줘야함... 불편
*
* */

@Slf4j
@Configuration
public class _03_ConditionalJobConfig {

    JobBuilderFactory jobBuilderFactory;
    StepBuilderFactory stepBuilderFactory;

    public _03_ConditionalJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    // step1 실패 ? -> step3 -> END
    // 그이외에는 ?  -> step2 -> step3 -> END
    @Bean
    public Job conditionalJob() {
        return jobBuilderFactory.get("conditionalJob")
                .start(conditionalStep1())
                    .on("FAILED") // step1이 실패했다면 (ExitStatus 가 FAILED 인 경우. step1에서 exitStatus를 faile로 뱉도록 해야 함.)
                    .to(conditionalStep3()) // step3 실행
                        .on("*") // step3 의 결과와 상관없이
                        .end() // 종료
                .from(conditionalStep1()) // step1 의 결과에
                    .on("*") // 상관없이 ( Failed는 이미 위에서 걸러짐)
                    .to(conditionalStep2()) // step2 실행
                    .next(conditionalStep3()) // step3 실행
                        .on("*") //step3 의 결과와 상관없이
                        .end() // 종료
                .end() // flow 종료
                .build();
    }

    @Bean
    public Step conditionalStep1() {
        return stepBuilderFactory.get("conditionalStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("########  conditionalStep1  ########  ");

                    stepContribution.setExitStatus(ExitStatus.FAILED); // ExitStatus
                    return RepeatStatus.FINISHED;})
                .build();
    }


    @Bean
    public Step conditionalStep2() {
        return stepBuilderFactory.get("conditionalStep2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("########  conditionalStep2  ########  ");
                    return RepeatStatus.FINISHED;})
                .build();
    }

    @Bean
    public Step conditionalStep3() {
        return stepBuilderFactory.get("conditionalStep3")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("########  conditionalStep3  ########  ");
                    return RepeatStatus.FINISHED;})
                .build();
    }


}
