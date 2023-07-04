package com.example.demo.business.impl;

import com.example.demo.domain.dto.SendMailRequest;
import com.example.demo.exceptions.ApiCallRuntimeException;
import com.example.demo.exceptions.JsonProcessingRuntimeException;
import com.example.demo.repository.impl.EmailEventRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StepApiPostAndEmailEventUpdateTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ApiServiceImpl apiService;

    @Mock
    private EmailEventRepositoryImpl emailEventRepository;

    @Mock
    private StepContribution contribution;

    @Mock
    private ChunkContext chunkContext;

    @Mock
    private StepContext stepContext;

    @Mock
    private ExecutionContext executionContext;

    @Mock
    private SendMailRequest sendMailRequest;

    @InjectMocks
    private StepApiPostAndEmailEventUpdate apiPostAndEmailEventUpdateStep;

    @Test
    public void testExecute() throws Exception {
        // Arrange
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getStepExecution()).thenReturn(mock(StepExecution.class));
        when(stepContext.getStepExecution().getJobExecution()).thenReturn(mock(JobExecution.class));
        when(stepContext.getStepExecution().getJobExecution().getExecutionContext()).thenReturn(executionContext);
        when(executionContext.get("message")).thenReturn("message");
        when(executionContext.get("insertedId")).thenReturn(1);
        when(executionContext.get("customDto")).thenReturn(sendMailRequest);
        when(sendMailRequest.getCustomerUniqueId()).thenReturn("uniqueId");
        when(sendMailRequest.getRequestId()).thenReturn("requestId");
        when(sendMailRequest.getBusinessLine()).thenReturn("businessLine");
        when(sendMailRequest.getSourceApplication()).thenReturn("sourceApp");
        when(objectMapper.writeValueAsString(sendMailRequest)).thenReturn("json");
        when(apiService.postToApi(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(ResponseEntity.ok().build());

        // Act
        RepeatStatus result = apiPostAndEmailEventUpdateStep.execute(contribution, chunkContext);

        // Assert
        verify(apiService, times(1)).postToApi(anyString(), eq("json"), eq("uniqueId"), eq("requestId"), eq("businessLine"), eq("sourceApp"), eq("PE"));
        verify(emailEventRepository, times(1)).updateEmailEventProcessingState(anyInt(), eq("PROCESADO"));
        verify(emailEventRepository, times(1)).updateEmailEventState(anyInt(), eq("COMPLETADO"));
        assertEquals(RepeatStatus.FINISHED, result);
    }

    @Test
    public void testExecute_WithJsonProcessingException() throws Exception {
        // Arrange
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getStepExecution()).thenReturn(mock(StepExecution.class));
        when(stepContext.getStepExecution().getJobExecution()).thenReturn(mock(JobExecution.class));
        when(stepContext.getStepExecution().getJobExecution().getExecutionContext()).thenReturn(executionContext);
        when(executionContext.get("message")).thenReturn("message");
        when(executionContext.get("insertedId")).thenReturn(1);
        when(executionContext.get("customDto")).thenReturn(mock(SendMailRequest.class));
        when(objectMapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("JSON error") {});

        // Act and Assert
        assertThrows(JsonProcessingRuntimeException.class, () -> {
            apiPostAndEmailEventUpdateStep.execute(contribution, chunkContext);
        });
    }

    @Test
    public void testExecute_WithHttpClientErrorException() throws Exception {
        // Arrange
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getStepExecution()).thenReturn(mock(StepExecution.class));
        when(stepContext.getStepExecution().getJobExecution()).thenReturn(mock(JobExecution.class));
        when(stepContext.getStepExecution().getJobExecution().getExecutionContext()).thenReturn(executionContext);
        when(executionContext.get("message")).thenReturn("message");
        when(executionContext.get("insertedId")).thenReturn(1);
        when(executionContext.get("customDto")).thenReturn(sendMailRequest);
        when(sendMailRequest.getCustomerUniqueId()).thenReturn("uniqueId");
        when(sendMailRequest.getRequestId()).thenReturn("requestId");
        when(sendMailRequest.getBusinessLine()).thenReturn("businessLine");
        when(sendMailRequest.getSourceApplication()).thenReturn("sourceApp");
        when(objectMapper.writeValueAsString(sendMailRequest)).thenReturn("json");
        when(apiService.postToApi(anyString(), eq("json"), eq("uniqueId"), eq("requestId"), eq("businessLine"), eq("sourceApp"), eq("PE")))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        // Act and Assert
        assertThrows(ApiCallRuntimeException.class, () -> {
            apiPostAndEmailEventUpdateStep.execute(contribution, chunkContext);
        });
    }
    @Test
    public void testExecute_WithHttpServerErrorException() throws Exception {
        // Arrange
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getStepExecution()).thenReturn(mock(StepExecution.class));
        when(stepContext.getStepExecution().getJobExecution()).thenReturn(mock(JobExecution.class));
        when(stepContext.getStepExecution().getJobExecution().getExecutionContext()).thenReturn(executionContext);
        when(executionContext.get("message")).thenReturn("message");
        when(executionContext.get("insertedId")).thenReturn(1);
        SendMailRequest sendMailRequest = mock(SendMailRequest.class);
        when(executionContext.get("customDto")).thenReturn(sendMailRequest);
        when(sendMailRequest.getCustomerUniqueId()).thenReturn("uniqueId");
        when(sendMailRequest.getRequestId()).thenReturn("requestId");
        when(sendMailRequest.getBusinessLine()).thenReturn("businessLine");
        when(sendMailRequest.getSourceApplication()).thenReturn("sourceApp");
        when(objectMapper.writeValueAsString(sendMailRequest)).thenReturn("json");
        when(apiService.postToApi(anyString(), eq("json"), eq("uniqueId"), eq("requestId"), eq("businessLine"), eq("sourceApp"), eq("PE")))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        // Act and Assert
        assertThrows(ApiCallRuntimeException.class, () -> {
            apiPostAndEmailEventUpdateStep.execute(contribution, chunkContext);
        });
    }

    @Test
    public void testExecute_WithResourceAccessException() throws Exception {
        // Arrange
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getStepExecution()).thenReturn(mock(StepExecution.class));
        when(stepContext.getStepExecution().getJobExecution()).thenReturn(mock(JobExecution.class));
        when(stepContext.getStepExecution().getJobExecution().getExecutionContext()).thenReturn(executionContext);
        when(executionContext.get("message")).thenReturn("message");
        when(executionContext.get("insertedId")).thenReturn(1);
        SendMailRequest sendMailRequest = mock(SendMailRequest.class);
        when(executionContext.get("customDto")).thenReturn(sendMailRequest);
        when(sendMailRequest.getCustomerUniqueId()).thenReturn("uniqueId");
        when(sendMailRequest.getRequestId()).thenReturn("requestId");
        when(sendMailRequest.getBusinessLine()).thenReturn("businessLine");
        when(sendMailRequest.getSourceApplication()).thenReturn("sourceApp");
        when(objectMapper.writeValueAsString(sendMailRequest)).thenReturn("json");
        when(apiService.postToApi(anyString(), eq("json"), eq("uniqueId"), eq("requestId"), eq("businessLine"), eq("sourceApp"), eq("PE")))
                .thenThrow(new ResourceAccessException("Access error"));

        // Act and Assert
        assertThrows(ApiCallRuntimeException.class, () -> {
            apiPostAndEmailEventUpdateStep.execute(contribution, chunkContext);
        });
    }

}
