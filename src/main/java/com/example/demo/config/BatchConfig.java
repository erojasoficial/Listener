package com.example.demo.config;

import com.example.demo.business.tasklet.ApiPostAndEmailEventUpdateTasklet;
import com.example.demo.business.tasklet.EmailEventProcessingTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private EmailEventProcessingTasklet emailEventProcessingTasklet;

    @Autowired
    private ApiPostAndEmailEventUpdateTasklet apiPostAndEmailEventUpdateTasklet;

    @Bean
    public Step stepOne() {
        return steps.get("emailProcessingStep")
                .tasklet(emailEventProcessingTasklet)
                .build();
    }

    @Bean
    public Step stepTwo() {
        return steps.get("apiEventUpdateStep")
                .tasklet(apiPostAndEmailEventUpdateTasklet)
                .build();
    }

    @Bean
    public Job myJob() {
        return jobs.get("myJob")
                .start(stepOne())
                .next(stepTwo())
                .build();
    }
}
