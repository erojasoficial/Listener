package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class MailListenerUtil {
    public HttpHeaders getHeaders(
            String xB3Traceid, String xB3Spanid, String xChannelId, String xOriginatingApplCode, String xCountryCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-b3-traceid", StringUtils.normalizeSpace(xB3Traceid));
        headers.add("x-b3-spanid", StringUtils.normalizeSpace(xB3Spanid));
        headers.add("x-channel-id", StringUtils.normalizeSpace(xChannelId));
        headers.add("x-originating-appl-code", StringUtils.normalizeSpace(xOriginatingApplCode));
        headers.add("x-country-code", StringUtils.normalizeSpace(xCountryCode));
        return headers;
    }
}