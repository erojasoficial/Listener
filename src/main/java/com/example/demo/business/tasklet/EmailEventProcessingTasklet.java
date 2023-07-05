package com.example.demo.business.tasklet;

import java.util.Optional;

import com.example.demo.constants.Constants;
import com.example.demo.converter.StringPositionToCustomDtoConverter;
import com.example.demo.converter.StringToStringPositionConverter;
import com.example.demo.domain.dto.SendMailRequest;
import com.example.demo.domain.entity.MessageEntity;
import com.example.demo.domain.model.StringPosition;
import com.example.demo.exceptions.MessageNotFoundException;
import com.example.demo.exceptions.MessageProcessingRuntimeException;
import com.example.demo.repository.impl.EmailEventRepositoryImpl;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailEventProcessingTasklet implements Tasklet {
    private final StringToStringPositionConverter stringConverter;
    private final StringPositionToCustomDtoConverter dtoConverter;
    private final EmailEventRepositoryImpl emailEventRepository;

    @Autowired
    public EmailEventProcessingTasklet(StringToStringPositionConverter stringConverter, StringPositionToCustomDtoConverter dtoConverter,
                                       EmailEventRepositoryImpl emailEventRepository) {
        this.stringConverter = stringConverter;
        this.dtoConverter = dtoConverter;
        this.emailEventRepository = emailEventRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        String message = (String) chunkContext.getStepContext().getJobParameters().get("message");
        Integer insertedId = null;

        if (message != null) {
            try {
                StringPosition[] positions = stringConverter.convert(message);
                SendMailRequest customDto = dtoConverter.convert(positions);

                insertedId = emailEventRepository.insertEmailEvent("dataQueueName", message);
                updateExecutionContext(chunkContext, insertedId, message, customDto);
            } catch (Exception e) {
                handleException(insertedId, e);
                throw new MessageProcessingRuntimeException("Error processing the message.", e);
            }
        }

        return RepeatStatus.FINISHED;
    }

    private void updateExecutionContext(ChunkContext chunkContext, Integer insertedId, String message, SendMailRequest customDto) {
        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        executionContext.put(Constants.INSERTED_ID, insertedId);
        executionContext.put(Constants.MESSAGE, message);
        executionContext.put(Constants.CUSTOM_DTO, customDto);
    }
    private void handleException(Integer insertedId, Exception e) {
        if (insertedId != null) {
            emailEventRepository.updateEmailEventProcessingState(insertedId, Constants.ERROR);
            emailEventRepository.updateEmailEventState(insertedId, Constants.FAILED);
            emailEventRepository.updateErrorDetail(insertedId, e.getMessage());
        } else {
            emailEventRepository.insertEmailEvent("dataQueueName", e.getMessage());
        }
    }
}