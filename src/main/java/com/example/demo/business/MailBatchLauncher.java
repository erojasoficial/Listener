package com.example.demo.business;

import com.example.demo.exceptions.JobLaunchRuntimeException;

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
public class MailBatchLauncher {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job myJob;

    public void launch(String message) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("message", message)
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
