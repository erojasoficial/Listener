package com.example.demo.business.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.domain.entity.MessageEntity;
import com.example.demo.exceptions.JobLaunchRuntimeException;
import com.example.demo.exceptions.MessageSaveRuntimeException;
import com.example.demo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobRestartException;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MyJobLauncherTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job myJob;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MyJobLauncher myJobLauncher;

    @Test
    public void testLaunch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // Arrange
        String message = "test message";
        MessageEntity mockMessageEntity = mock(MessageEntity.class);
        when(mockMessageEntity.getId()).thenReturn(1L);
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);

        // Act
        myJobLauncher.launch(message);

        // Assert
        verify(messageRepository, times(1)).save(any(MessageEntity.class));
        verify(jobLauncher, times(1)).run(eq(myJob), any(JobParameters.class));
    }

    @Test
    public void testLaunch_WithMessageSaveException() {
        // Arrange
        String message = "test message";
        when(messageRepository.save(any(MessageEntity.class))).thenThrow(new RuntimeException("DB error"));

        // Act and Assert
        assertThrows(MessageSaveRuntimeException.class, () -> {
            myJobLauncher.launch(message);
        });
    }

    @Test
    public void testLaunch_WithJobLaunchException() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // Arrange
        String message = "test message";
        MessageEntity mockMessageEntity = mock(MessageEntity.class);
        when(mockMessageEntity.getId()).thenReturn(1L);
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);
        doThrow(new JobParametersInvalidException("Job parameters error")).when(jobLauncher).run(eq(myJob), any(JobParameters.class));

        // Act and Assert
        assertThrows(JobLaunchRuntimeException.class, () -> {
            myJobLauncher.launch(message);
        });
    }
}
