package com.example.demo.config;

import com.example.demo.business.impl.StepEmailEventProcessing;
import com.example.demo.business.impl.StepApiPostAndEmailEventUpdate;
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
    private StepEmailEventProcessing step1Tasklet;

    @Autowired
    private StepApiPostAndEmailEventUpdate step2Tasklet;

    @Bean
    public Step stepOne() {
        return steps.get("stepOne")
                .tasklet(step1Tasklet)
                .build();
    }

    @Bean
    public Step stepTwo() {
        return steps.get("stepTwo")
                .tasklet(step2Tasklet)
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
