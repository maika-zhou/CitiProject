package com.paramJob;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


/**
 *  通过监听器 传入参数，随后运行
 */
@Configuration
public class JobConfiguration implements StepExecutionListener {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Map<String,JobParameter> params;

    @Bean
    public Job paramJob()
    {
        return jobBuilderFactory.get("paramJob")
                                .start(step1())
                                .build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        params = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }


    //Job执行的是step，所以参数肯定是在step中使用，我们只需要给step传递参数
    @Bean
    public Step step1()
    {
        return stepBuilderFactory.get("step1")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Step1 --------->");
                        System.out.println(params.get("info"));
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
