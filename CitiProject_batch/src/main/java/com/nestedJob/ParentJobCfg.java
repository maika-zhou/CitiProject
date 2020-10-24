package com.nestedJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


/**
 *      JobCfg1 -> 创建一个job 保护childJob1Step1
 *      JobCfg2 -> 创建一个job 保护childJob2Step1 & childJob2Step2
 *
 *      ParentJobCfg保护以上二个job，依次运行。 记得在application.yml 添加spring.batch.job.names: parentJob
 *
 *
 */
@Configuration
public class ParentJobCfg {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Job childJob1;
    @Autowired
    private Job childJob2;
    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager transactionManager)
    {
        return jobBuilderFactory.get("parentJob")
                                .start(childParentJob1(jobRepository,transactionManager))
                                .next(childParentJob2(jobRepository,transactionManager))
                                .build();
    }

    //返回的是Job类型的Steop
    public Step childParentJob1(JobRepository jobRepository, PlatformTransactionManager transactionManager)
    {
        return new JobStepBuilder(new StepBuilder("childParentJob1"))
                    .job(childJob1)
                    .launcher(jobLauncher)
                    .repository(jobRepository)
                    .transactionManager(transactionManager)
                    .build();
    }

    public Step childParentJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager)
    {
        return new JobStepBuilder(new StepBuilder("childParentJob2"))
                .job(childJob2)
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }
}
