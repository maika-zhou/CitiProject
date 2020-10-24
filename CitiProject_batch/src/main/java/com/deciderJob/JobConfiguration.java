package com.deciderJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *      很多时候，需要根据自己的需求来觉得运行哪些job，
 *      定义自己的Decider决策器，如果偶数执行step2,基数执行step3，step3执行完毕后再走一遍Decider
 *
 *
 */
@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job deciderJobDemo()
    {
        return jobBuilderFactory.get("deciderJobDemo")
                                .start(step1())
                                .next(myDecider())
                                .from(myDecider()).on("even").to(step2())
                                .from(myDecider()).on("odd").to(step3())
                                .from(step3()).on("*").to(myDecider())  //无论step3返回什么，都执行决策器
                                .end().build();
    }

    @Bean
    public Step step1()
    {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Step1 --------->");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step2()
    {
        return stepBuilderFactory.get("step2")
                .tasklet(
                            (stepContribution,chunkContext)->{
                                System.out.println("Step2 --------->");
                                return RepeatStatus.FINISHED;
                            }

                ).build();
    }

    @Bean
    public Step step3()
    {
        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Step3 --------->");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }



    @Bean
    public JobExecutionDecider myDecider()
    {
        return new MyDecider();
    }

}
