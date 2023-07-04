package com.example.demo.domain.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ContentMapFactory {
    public Map<String, Object> create(StringPosition[] positions) {
        Map<String, Object> contentMap = new HashMap<>();
        for (int i = 13; i < positions.length - 1; i += 2) {
            String key = positions[i].getValue();
            String value = positions[i + 1].getValue();

            if (key != null && !key.trim().isEmpty() && value != null && !value.trim().isEmpty()) {
                key = key.replaceAll("\\s+$", "");
                value = value.replaceAll("\\s+$", "");
                contentMap.put(key, value);
            }
        }
        return contentMap;
    }
}

