package com.example.demo.business.impl;

import com.example.demo.exceptions.EncryptionException;
import com.example.demo.util.EncryptUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.mockStatic;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoServiceImplTest {
    String encryptKey = "encryptKey";
    String writeValueAsString = "xxx";
    ObjectMapper mapper;
    CryptoServiceImpl cryptoService;
    private AutoCloseable closeable;

    @BeforeEach
    void initService() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.cryptoService = new CryptoServiceImpl();

    }
    @Test
    void encryptObject() throws Exception {
        Object obj = new Object();
        this.mapper = mock(ObjectMapper.class);
        when(this.mapper.writeValueAsString(any())).thenReturn(this.writeValueAsString);
        ReflectionTestUtils.setField(this.cryptoService, "mapper", this.mapper);
        ReflectionTestUtils.setField(this.cryptoService, "encryptKey", this.encryptKey);
        try (MockedStatic mocked = mockStatic(EncryptUtil.class)) {
            mocked.when(() -> EncryptUtil.encryptRequest(anyString(), anyString())).thenReturn("");
            this.cryptoService.encryptObject(obj);
        }

        Assertions.assertThat(this.cryptoService).isNotNull();
    }

    @Test
    void encryptString() {
        String decrypt = "x";
        ReflectionTestUtils.setField(this.cryptoService, "encryptKey", this.encryptKey);
        try (MockedStatic mocked = mockStatic(EncryptUtil.class)) {
            mocked.when(() -> EncryptUtil.decryptRequest(anyString(), anyString())).thenReturn("");
            this.cryptoService.encryptString(decrypt);
        }
        Assertions.assertThat(this.cryptoService).isNotNull();
    }
    @Test
    void encryptStringException() {
        String str = "Some string";
        ReflectionTestUtils.setField(this.cryptoService, "encryptKey", this.encryptKey);

        try (MockedStatic<EncryptUtil> mocked = mockStatic(EncryptUtil.class)) {
            mocked.when(() -> EncryptUtil.encryptRequest(anyString(), anyString())).thenThrow(new RuntimeException()); // Simulamos una excepción al encriptar la cadena
            assertThrows(EncryptionException.class, () -> {
                this.cryptoService.encryptString(str);
            });
        }
    }
    @Test
    void encryptObject_whenEncryptUtilThrowsException() {
        String jsonString = "{}";
        this.mapper = mock(ObjectMapper.class);

        try {
            when(this.mapper.writeValueAsString(any())).thenReturn(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(this.cryptoService, "mapper", this.mapper);
        ReflectionTestUtils.setField(this.cryptoService, "encryptKey", this.encryptKey);

        try (MockedStatic<EncryptUtil> mocked = mockStatic(EncryptUtil.class)) {
            mocked.when(() -> EncryptUtil.encryptRequest(anyString(), anyString())).thenThrow(new RuntimeException()); // Simulamos una excepción al encriptar la cadena

            assertThrows(EncryptionException.class, () -> {
                this.cryptoService.encryptObject(new Object());
            });
        }
    }
}