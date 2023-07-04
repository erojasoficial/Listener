package com.example.demo.util;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.tika.io.FilenameUtils;

@SuppressWarnings("unused")
@Slf4j
final public class EncryptUtil {
    private EncryptUtil() {
    }
    @SneakyThrows
    public static String encryptRequest(String request, String encryptKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            synchronized (cipher) {
                byte[] encoded = request.getBytes(StandardCharsets.UTF_8);

                byte[] bk = Hex.decodeHex(encryptKey);
                var key = new SecretKeySpec(bk, 0, bk.length, "AES");

                byte[] initVector = new byte[12];
                (new SecureRandom()).nextBytes(new byte[12]);
                var spec = new GCMParameterSpec(128, initVector);

                cipher.init(Cipher.ENCRYPT_MODE, key, spec);
                int cipherTextSize = initVector.length + cipher.getOutputSize(encoded.length);
                byte[] ciphertext = new byte[cipherTextSize];

                int outputOffset = initVector.length;
                System.arraycopy(initVector, 0, ciphertext, 0, initVector.length);

                cipher.doFinal(encoded, 0, encoded.length, ciphertext, outputOffset);
                return Base64.getEncoder().encodeToString(ciphertext);
            }
        }
        catch (DecoderException e) {
            throw e;
        }
    }
    @SneakyThrows
    public static String decryptRequest(String request, String encryptKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            synchronized (cipher) {
                log.debug("request and encryptKey {} {}", request, encryptKey);
                byte[] bk = Hex.decodeHex(encryptKey);
                var key = new SecretKeySpec(bk, 0, bk.length, "AES");

                byte[] cipherText = Base64.getDecoder().decode(request);
                byte[] initVector = Arrays.copyOfRange(cipherText, 0, 12);
                var spec = new GCMParameterSpec(128, initVector);
                cipher.init(Cipher.DECRYPT_MODE, key, spec);
                byte[] plaintext = cipher.doFinal(cipherText, 12, cipherText.length - 12);
                return new String(plaintext, StandardCharsets.UTF_8);
            }
        }
        catch (InvalidAlgorithmParameterException e) {
            log.error("InvalidAlgorithmParameterException {}", e);
            throw e;
        }
    }
    @SneakyThrows
    public static PrivateKey getPrivateKey(String keyPath, String pwKeystore, String pwKey, String aliasKey) {

        if (!Files.exists(Paths.get(FilenameUtils.normalize(keyPath)))) {
            throw new Exception("Passport's file not found!");
        }

        var fileKeyPassport = new File(FilenameUtils.normalize(keyPath));

        try (InputStream inputStream = Files.newInputStream(Paths.get(FilenameUtils.normalize(fileKeyPassport.getAbsolutePath())))) {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(inputStream, pwKey.toCharArray());
            return (PrivateKey) keystore.getKey(aliasKey, pwKeystore.toCharArray());
        }
        catch (Exception e) {
            log.error("Passport Load Error", e);
            throw e;
        }
    }
    @SneakyThrows
    public static Key getSecretKey(String keyPath, String pwKey, String pwKeystore, String aliasKey) {
        char[] ksPass = pwKeystore.toCharArray();
        char[] aesPass = pwKey.toCharArray();
        try (InputStream fis = Files.newInputStream(Paths.get(FilenameUtils.normalize(keyPath)))) {
            KeyStore ks = KeyStore.getInstance("JCEKS");
            ks.load(fis, ksPass);
            return ks.getKey(aliasKey, aesPass);
        }
        catch (KeyStoreException e) {
            throw e;
        }
    }
}