package com.example.demo.business.impl;

import com.example.demo.business.CryptoService;
import com.example.demo.constants.MessageErrorHelper;
import com.example.demo.exceptions.EncryptionException;
import com.example.demo.util.EncryptUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CryptoServiceImpl implements CryptoService {
    @Autowired
    String encryptKey;
    @Autowired
    ObjectMapper mapper;
    @Override
    public String encryptObject(Object object) {
        try {
            String jsonRequest = this.mapper.writeValueAsString(object);
            return EncryptUtil.encryptRequest(jsonRequest, this.encryptKey);
        }
        catch (Exception e) {
            log.error("Encrypt Request failed!", e);
            throw new EncryptionException(MessageErrorHelper.ENCRYPTION_MESSAGE, e);
        }
    }
    @Override
    public String encryptString(String encryptedJson) {
        try {
            return EncryptUtil.encryptRequest(encryptedJson, this.encryptKey);
        }
        catch (Exception e) {
            log.error("Decrypt Request failed!", e);
            throw new EncryptionException(MessageErrorHelper.DECRYPTION_MESSAGE, e);
        }
    }

}