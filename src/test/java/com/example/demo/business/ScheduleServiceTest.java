package com.example.demo.business;

import com.example.demo.exceptions.ScheduleServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    MyJobLauncher myJobLauncher;

    @InjectMocks
    ScheduleService scheduleService;

    @Test
    void testSendMessageException() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        doThrow(new JobParametersInvalidException("Job parameters are invalid"))
                .when(myJobLauncher).launch(any(String.class));

        assertThrows(ScheduleServiceException.class, () -> {
            scheduleService.sendMessage();
        });
    }
}