package com.example.demo.business.impl;

import com.example.demo.business.CryptoService;
import com.example.demo.constants.Constants;
import com.example.demo.domain.dto.SendMailRequest;
import com.example.demo.domain.model.EncryptedValue;
import com.example.demo.exceptions.ApiCallRuntimeException;
import com.example.demo.exceptions.JsonProcessingRuntimeException;
import com.example.demo.repository.impl.EmailEventRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Component
public class StepApiPostAndEmailEventUpdate implements Tasklet {
    @Value("${app.apiUrl}")
    private String apiUrl;
    private final CryptoService cryptoService;
    private final ObjectMapper objectMapper;
    private final ApiServiceImpl apiService;
    private final EmailEventRepositoryImpl emailEventRepository;

    @Autowired
    public StepApiPostAndEmailEventUpdate(ObjectMapper objectMapper, ApiServiceImpl apiService, EmailEventRepositoryImpl emailEventRepository, CryptoService cryptoService) {
        this.objectMapper = objectMapper;
        this.apiService = apiService;
        this.emailEventRepository = emailEventRepository;
        this.cryptoService = cryptoService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Optional<String> message = Optional.ofNullable((String) chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getExecutionContext().get(Constants.MESSAGE));
        Optional<Integer> insertedId = Optional.ofNullable((Integer) chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getExecutionContext().get(Constants.INSERTED_ID));
        Optional<SendMailRequest> customDto = Optional.ofNullable((SendMailRequest) chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getExecutionContext().get(Constants.CUSTOM_DTO));

        System.out.println("message2: " + message.orElse(""));
        System.out.println("InsertedId in Step2Tasklet: " + insertedId.orElse(null));

        try {
            String customDtoJson = objectMapper.writeValueAsString(customDto.orElseThrow());
            System.out.println("customDto in Step2Tasklet: " + customDtoJson); // This is the JSON
            String encryptedJsonString = this.cryptoService.encryptString(customDtoJson);
            EncryptedValue encryptedValue = new EncryptedValue().encryptedValue(encryptedJsonString);
            String encryptedValueJson = objectMapper.writeValueAsString(encryptedValue);
            System.out.println("encryptedValueJson: " + encryptedValueJson);
            ResponseEntity<String> responseEntity = apiService.postToApi(
                    apiUrl,
                    encryptedValueJson,
                    customDto.orElseThrow().getCustomerUniqueId(),
                    customDto.orElseThrow().getRequestId(),
                    customDto.orElseThrow().getBusinessLine(),
                    customDto.orElseThrow().getSourceApplication(),
                    Constants.COUNTRY_CODE
            );

            System.out.println("Response from API: " + responseEntity.getBody());
            System.out.println("Response status code: " + responseEntity.getStatusCodeValue());

            int statusCode = responseEntity.getStatusCodeValue();
            if (statusCode == 200 || statusCode == 201) {
                handleSuccess(insertedId.orElse(null), customDto.orElseThrow(), encryptedJsonString);
            }
        } catch (JsonProcessingException e) {
            handleError(insertedId.orElse(null), e);
            throw new JsonProcessingRuntimeException("Error processing JSON in Step2Tasklet.", e);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            handleError(insertedId.orElse(null), e);
            throw new ApiCallRuntimeException("Error in HTTP request in Step2Tasklet.", e);
        } catch (ResourceAccessException e) {
            handleError(insertedId.orElse(null), e);
            throw new ApiCallRuntimeException("Failed to access the API in Step2Tasklet.", e);
        }

        return RepeatStatus.FINISHED;
    }

    private void handleSuccess(Integer insertedId, SendMailRequest customDto, String encryptedJsonString) throws JsonProcessingException {
        if (insertedId != null) {
            emailEventRepository.updateEmailEventProcessingState(insertedId, Constants.PROCESSED);
            emailEventRepository.updateEmailEventState(insertedId, Constants.COMPLETED);
            emailEventRepository.updateEmailEvent(insertedId, customDto, encryptedJsonString);
        }
    }

    private void handleError(Integer insertedId, Exception e) {
        if (insertedId != null) {
            emailEventRepository.updateEmailEventProcessingState(insertedId, Constants.ERROR);
            emailEventRepository.updateEmailEventState(insertedId, Constants.FAILED);
            emailEventRepository.updateErrorDetail(insertedId, e.getMessage());
        }
    }
}
