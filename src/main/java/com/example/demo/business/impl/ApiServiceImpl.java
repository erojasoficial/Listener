package com.example.demo.business.impl;

import com.example.demo.business.ApiService;
import com.example.demo.exceptions.HttpCallRuntimeException;
import com.example.demo.exceptions.NetworkRuntimeException;
import com.example.demo.util.MailListenerUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiService {
    private final RestTemplate restTemplate;
    private final MailListenerUtil mailListenerUtil;

    public ApiServiceImpl(RestTemplate restTemplate, MailListenerUtil mailListenerUtil) {
        this.restTemplate = restTemplate;
        this.mailListenerUtil = mailListenerUtil;
    }

    public ResponseEntity<String> postToApi(String url, String jsonBody,
                                            String xB3Traceid, String xB3Spanid, String xChannelId,
                                            String xOriginatingApplCode, String xCountryCode) {
        HttpHeaders headers = mailListenerUtil.getHeaders(
                xB3Traceid, xB3Spanid, xChannelId, xOriginatingApplCode, xCountryCode);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return responseEntity;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new HttpCallRuntimeException("Error in the HTTP request.", e);
        } catch (ResourceAccessException e) {
            throw new NetworkRuntimeException("Failed to access the API.", e);
        }
    }
}

