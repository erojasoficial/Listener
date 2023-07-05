package com.example.demo.business.impl;

import com.example.demo.converter.StringPositionToCustomDtoConverter;
import com.example.demo.converter.StringToStringPositionConverter;
import com.example.demo.domain.entity.MessageEntity;
import com.example.demo.exceptions.MessageProcessingRuntimeException;
import com.example.demo.repository.impl.EmailEventRepositoryImpl;
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

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StepEmailEventProcessingTest {

    @Mock
    private StringToStringPositionConverter stringConverter;

    @Mock
    private StringPositionToCustomDtoConverter dtoConverter;

    @Mock
    private EmailEventRepositoryImpl emailEventRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private StepContribution contribution;

    @Mock
    private ChunkContext chunkContext;

    @Mock
    private StepContext stepContext;

    @Mock
    private ExecutionContext executionContext;

    @InjectMocks
    private StepEmailEventProcessing emailEventProcessingStep;

    @Test
    public void testExecute() throws Exception {
        // Arrange
        Long messageId = 1L;
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getJobParameters()).thenReturn(Map.of("messageId", messageId));
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage("message");
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(messageEntity));
        when(stepContext.getStepExecution()).thenReturn(mock(StepExecution.class));
        when(stepContext.getStepExecution().getJobExecution()).thenReturn(mock(JobExecution.class));
        when(stepContext.getStepExecution().getJobExecution().getExecutionContext()).thenReturn(executionContext);

        // Act
        RepeatStatus result = emailEventProcessingStep.execute(contribution, chunkContext);

        // Assert
        verify(messageRepository, times(1)).findById(messageId);
        verify(stringConverter, times(1)).convert(anyString());
        verify(dtoConverter, times(1)).convert(any());
        verify(emailEventRepository, times(1)).insertEmailEvent(anyString(), anyString());
        assertEquals(RepeatStatus.FINISHED, result);
    }

    @Test
    public void testExecute_WithNoMessageId() {
        // Arrange
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getJobParameters()).thenReturn(Map.of());

        // Act and Assert
        assertThrows(MessageProcessingRuntimeException.class, () -> {
            emailEventProcessingStep.execute(contribution, chunkContext);
        });
    }

    @Test
    public void testExecute_WithNoMessageEntity() {
        // Arrange
        Long messageId = 1L;
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getJobParameters()).thenReturn(Map.of("messageId", messageId));
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(MessageProcessingRuntimeException.class, () -> {
            emailEventProcessingStep.execute(contribution, chunkContext);
        });
    }

    @Test
    public void testExecute_WithProcessingException() {
        // Arrange
        Long messageId = 1L;
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getJobParameters()).thenReturn(Map.of("messageId", messageId));
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage("message");
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(messageEntity));
        when(stringConverter.convert(anyString())).thenThrow(new RuntimeException("Processing error"));

        // Act and Assert
        assertThrows(MessageProcessingRuntimeException.class, () -> {
            emailEventProcessingStep.execute(contribution, chunkContext);
        });
    }
}
