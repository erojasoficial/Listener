package com.example.demo.business;

import org.springframework.http.ResponseEntity;

public interface ApiService {
    ResponseEntity<String> postToApi(String url, String jsonBody,
                                     String xB3Traceid, String xB3Spanid, String xChannelId,
                                     String xOriginatingApplCode, String xCountryCode);
}
