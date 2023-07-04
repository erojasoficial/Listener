package com.example.demo.business.impl;

import com.example.demo.domain.entity.MessageEntity;
import com.example.demo.exceptions.JobLaunchRuntimeException;
import com.example.demo.exceptions.MessageSaveRuntimeException;
import com.example.demo.repository.MessageRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyJobLauncher {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job myJob;
    @Autowired
    MessageRepository messageRepository;

    public void launch(String message) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message);
        try {
            messageEntity = messageRepository.save(messageEntity);
        } catch (Exception e) {
            throw new MessageSaveRuntimeException("Error saving the message to the database.", e);
        }

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("messageId", messageEntity.getId())
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(myJob, jobParameters);
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException e) {
            throw new JobLaunchRuntimeException("Error launching the job.", e);
        }
    }
}
