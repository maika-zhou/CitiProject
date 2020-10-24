package com.flowJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  使用Flow，把step1、step2放到flow中，随后通过job启动flow再启动step3
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
    public Flow jobFlow()
    {
        return new FlowBuilder<Flow>("jobFlow")
                .start(step1())
                .next(step2())
                //.next(step3())
                .build();
    }

    @Bean
    public Job jobFlowDemo2()
    {
        return jobBuilderFactory.get("jobFlowDemo2")
                .start(jobFlow())
                .next(step3()).end()
                .build();
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



}
