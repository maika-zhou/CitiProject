package com.nestedJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *      一个job可以嵌套另一个job
 *      被嵌套的job称为子job，外部Job为父job
 *      子job不能单独运行，需要父job来启动
 *
 *
 */
@Configuration
public class JobCfg1 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job childJob1()
    {
        return jobBuilderFactory.get("childJob1")
                                .start(childJob1Step1())
                                .build();
    }


    @Bean
    public Step childJob1Step1()
    {
        return stepBuilderFactory.get("childJob1Step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJob1Step1 --------->");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
