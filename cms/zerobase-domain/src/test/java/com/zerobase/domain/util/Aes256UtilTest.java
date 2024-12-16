package com.zerobase.domain.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("Hello world");
        assertEquals(Aes256Util.decrypt(encrypt), "Hello world");
    }


    @Test
    void encrypt2() {
        String originalText = "Hello world";

        // 암호화
        String encryptedText = Aes256Util.encrypt(originalText);
        assertNotNull(encryptedText, "Encryption should not return null");
        System.out.println("Encrypted: " + encryptedText);

        // 복호화
        String decryptedText = Aes256Util.decrypt(encryptedText);
        assertNotNull(decryptedText, "Decryption should not return null");
        System.out.println("Decrypted: " + decryptedText);

        // 원본 텍스트와 복호화된 텍스트 비교
        assertEquals(originalText, decryptedText, "Decrypted text should match the original text");
    }

    @Test
    void encryptDecryptTest() {
        String originalText = "Hello world";
        String koreanText = "안녕하세요";

        // 영어 텍스트 암호화/복호화 테스트
        String encryptedText = Aes256Util.encrypt(originalText);
        assertNotNull(encryptedText, "Encryption should not return null");
        String decryptedText = Aes256Util.decrypt(encryptedText);
        assertNotNull(decryptedText, "Decryption should not return null");
        assertEquals(originalText, decryptedText, "Decrypted text should match the original text");

        // 한글 텍스트 암호화/복호화 테스트
        String encryptedKorean = Aes256Util.encrypt(koreanText);
        assertNotNull(encryptedKorean, "Korean text encryption should not return null");
        String decryptedKorean = Aes256Util.decrypt(encryptedKorean);
        assertNotNull(decryptedKorean, "Korean text decryption should not return null");
        assertEquals(koreanText, decryptedKorean, "Decrypted Korean text should match the original Korean text");
    }
}