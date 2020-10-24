package com.splitJob;

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
import org.springframework.core.task.SimpleAsyncTaskExecutor;


/**
 *      create 2 flows with multiple steps
 *      create a job start 2 flow asynchronously using split
 *      launch the job to see result
 */
@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job splitJobDemo()
    {
        return jobBuilderFactory.get("splitJobDemo")
                                .start(jobFlow1())
                                .split(new SimpleAsyncTaskExecutor())
                                .add(jobFlow2())
                                .end().build();



    }

    @Bean
    public Flow jobFlow1()
    {
        return new FlowBuilder<Flow>("jobFlow1")
                .start(step1())
                .build();
    }

    @Bean
    public Flow jobFlow2()
    {
        return new FlowBuilder<Flow>("jobFlow2")
                .start(step2())
                .next(step3())
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
