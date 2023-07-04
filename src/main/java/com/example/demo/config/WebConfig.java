package com.example.demo.config;

import com.example.demo.exceptions.WebConfigException;
import com.example.demo.util.EncryptUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.tika.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;

@Slf4j
@Configuration
public class WebConfig {

    @Value("${app.encryptor.props.k}")
    String encryptorPropsK;

    @Value("${app.encryptor.props.iv}")
    String encryptorPropsIv;

    @Value("${app.encryptor.params.p}")
    String encryptorParamsP;

    @Value("${app.encryptor.params.kp}")
    String encryptorParamsKp;

    @Value("${app.encryptor.params.path}")
    String encryptorParamsPath;

    @Value("${app.encryptor.params.k}")
    String encryptorParamsK;

    @Value("${app.encryptor.keystore-path}")
    String encryptorKeyPath;

    @SneakyThrows
    @Bean
    String encryptKey() {

        if (!Files.exists(Paths.get(FilenameUtils.normalize(this.encryptorKeyPath)))) {
            throw new WebConfigException("Encryptor's file not found!");
        }

        var passwordKeystore = encryptorParamsP();
        var passwordKey = encryptorParamsKp();
        var aliasKey = encryptorParamsK();
        Key key = EncryptUtil.getSecretKey(this.encryptorKeyPath, passwordKey, passwordKeystore, aliasKey);
        var secretKey2 = (SecretKey) key;
        return Hex.encodeHexString(secretKey2.getEncoded());

    }

    @SneakyThrows
    @Bean
    String encryptorParamsK() {
        return EncryptUtil.decryptRequest(this.encryptorParamsK, this.encryptorPropsK);
    }

    @SneakyThrows
    @Bean
    String encryptorParamsKp() {
        return EncryptUtil.decryptRequest(this.encryptorParamsKp, this.encryptorPropsK);
    }

    @SneakyThrows
    @Bean
    String encryptorParamsP() {
        return EncryptUtil.decryptRequest(this.encryptorParamsP, this.encryptorPropsK);
    }

}