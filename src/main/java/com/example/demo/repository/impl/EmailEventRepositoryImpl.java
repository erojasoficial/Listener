package com.example.demo.repository.impl;

import com.example.demo.domain.dto.SendMailRequest;
import com.example.demo.domain.entity.EmailEvents;
import com.example.demo.repository.EmailEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class EmailEventRepositoryImpl {

    @Autowired
    private EmailEventsRepository emailEventsRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public int insertEmailEvent(String dataQueueName, String inputDataQueue) {
        EmailEvents emailEvents = new EmailEvents();
        emailEvents.setDataQueueName(dataQueueName);
        emailEvents.setInputDataQueue(inputDataQueue);
        EmailEvents saved = emailEventsRepository.save(emailEvents);
        return saved.getEventId();
    }

    public void updateEmailEventProcessingState(int eventId, String processingState) {
        Optional<EmailEvents> optionalEmailEvents = emailEventsRepository.findById(eventId);
        optionalEmailEvents.ifPresent(emailEvents -> {
            emailEvents.setProcessingState(processingState);
            emailEvents.setProcessingDate(new Date());
            emailEventsRepository.save(emailEvents);
        });
    }

    public void updateEmailEventState(int eventId, String eventState) {
        Optional<EmailEvents> optionalEmailEvents = emailEventsRepository.findById(eventId);
        optionalEmailEvents.ifPresent(emailEvents -> {
            emailEvents.setEventState(eventState);
            emailEventsRepository.save(emailEvents);
        });
    }

    public void updateErrorDetail(int eventId, String errorDetail) {
        Optional<EmailEvents> optionalEmailEvents = emailEventsRepository.findById(eventId);
        optionalEmailEvents.ifPresent(emailEvents -> {
            emailEvents.setErrorDetails(errorDetail);
            emailEventsRepository.save(emailEvents);
        });
    }
    public void updateEmailEvent(int eventId, SendMailRequest sendMailRequest, String encryptedEmail) {
        Optional<EmailEvents> optionalEmailEvents = emailEventsRepository.findById(eventId);
        optionalEmailEvents.ifPresent(emailEvents -> {
            emailEvents.setDestinationEmail(sendMailRequest.getDeliveryMethods().get(0).getDeliveryDestination());
            emailEvents.setEmailSubject(sendMailRequest.getTemplate().getTitle());
            try {
                emailEvents.setEmailJson(objectMapper.writeValueAsString(sendMailRequest));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            emailEvents.setEncryptedEmail(encryptedEmail);
            emailEventsRepository.save(emailEvents);
        });
    }
}
