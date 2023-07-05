package com.example.demo.business.impl;

import com.example.demo.exceptions.HttpCallRuntimeException;
import com.example.demo.exceptions.NetworkRuntimeException;
import com.example.demo.util.MailListenerUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

    @Autowired
    private ApiServiceImpl apiService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private MailListenerUtil mailListenerUtil;

    @Test
    public void postToApi_ShouldReturnResponseEntity() {
        String url = "http://api.example.com";
        String jsonBody = "test";
        String traceId = "traceId";
        String spanId = "spanId";
        String channelId = "channelId";
        String originatingApplCode = "originatingApplCode";
        String countryCode = "PE";
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> responseEntity = new ResponseEntity<>("success", HttpStatus.OK);

        Mockito.when(mailListenerUtil.getHeaders(traceId, spanId, channelId, originatingApplCode, countryCode)).thenReturn(headers);
        Mockito.when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class))).thenReturn(responseEntity);

        ResponseEntity<String> result = apiService.postToApi(url, jsonBody, traceId, spanId, channelId, originatingApplCode, countryCode);

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals("success", result.getBody());
    }

    @Test
    public void postToApi_ShouldThrowHttpCallRuntimeException() {
        String url = "http://api.example.com";
        String jsonBody = "test";
        String traceId = "traceId";
        String spanId = "spanId";
        String channelId = "channelId";
        String originatingApplCode = "originatingApplCode";
        String countryCode = "PE";
        HttpHeaders headers = new HttpHeaders();

        when(mailListenerUtil.getHeaders(traceId, spanId, channelId, originatingApplCode, countryCode)).thenReturn(headers);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenThrow(HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "BAD_REQUEST", headers, null, null));

        assertThrows(HttpCallRuntimeException.class, () -> apiService.postToApi(url, jsonBody, traceId, spanId, channelId, originatingApplCode, countryCode));
    }

    @Test
    public void postToApi_ShouldThrowNetworkRuntimeException() {
        String url = "http://api.example.com";
        String jsonBody = "test";
        String traceId = "traceId";
        String spanId = "spanId";
        String channelId = "channelId";
        String originatingApplCode = "originatingApplCode";
        String countryCode = "PE";
        HttpHeaders headers = new HttpHeaders();

        when(mailListenerUtil.getHeaders(traceId, spanId, channelId, originatingApplCode, countryCode)).thenReturn(headers);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class))).thenThrow(new ResourceAccessException("ResourceAccessException"));

        assertThrows(NetworkRuntimeException.class, () -> apiService.postToApi(url, jsonBody, traceId, spanId, channelId, originatingApplCode, countryCode));
    }
}
