package com.example.demo.business.impl;

import com.example.demo.constants.Constants;
import com.example.demo.converter.StringPositionToCustomDtoConverter;
import com.example.demo.converter.StringToStringPositionConverter;
import com.example.demo.domain.dto.SendMailRequest;
import com.example.demo.domain.entity.MessageEntity;
import com.example.demo.domain.model.StringPosition;
import com.example.demo.exceptions.MessageNotFoundException;
import com.example.demo.exceptions.MessageProcessingRuntimeException;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.impl.EmailEventRepositoryImpl;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StepEmailEventProcessing implements Tasklet {
    private final StringToStringPositionConverter stringConverter;
    private final StringPositionToCustomDtoConverter dtoConverter;
    private final EmailEventRepositoryImpl emailEventRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public StepEmailEventProcessing(StringToStringPositionConverter stringConverter, StringPositionToCustomDtoConverter dtoConverter,
                                    EmailEventRepositoryImpl emailEventRepository, MessageRepository messageRepository) {
        this.stringConverter = stringConverter;
        this.dtoConverter = dtoConverter;
        this.emailEventRepository = emailEventRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Optional<Long> messageId = Optional.ofNullable((Long) chunkContext.getStepContext().getJobParameters().get("messageId"));
        Integer insertedId = null;

        try {
            if (messageId.isPresent()) {
                MessageEntity messageEntity = messageRepository.findById(messageId.get()).orElseThrow(() ->
                        new MessageNotFoundException("Message not found for the ID: " + messageId.get()));
                String message = messageEntity.getMessage();
                StringPosition[] positions = stringConverter.convert(message);
                SendMailRequest customDto = dtoConverter.convert(positions);

                insertedId = emailEventRepository.insertEmailEvent("dataQueueName", message);
                updateExecutionContext(chunkContext, insertedId, message, customDto);
            } else {
                throw new MessageNotFoundException("No message ID provided.");
            }
        } catch (Exception e) {
            handleException(insertedId, e);
            throw new MessageProcessingRuntimeException("Error processing the message.", e);
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
