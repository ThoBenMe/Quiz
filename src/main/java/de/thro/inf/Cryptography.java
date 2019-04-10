package de.thro.inf;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Simple AES encryption used from javax.crypto.
 *
 * @author Daniel Hentzschel on 15.06.2018.
 */
public class Cryptography {
    private String key;

    private static final String ALGORITHM = "Rijndael";
    private Cipher cipher;

    /**
     * Constructor initializes key string by given parameter.
     *
     * @param key key string
     */
    public Cryptography(String key) {
        this.key = key;
    }

    /**
     * Encrypts given plain text to aes encrypted string.
     *
     * @param plainText encryptable string
     */
    public byte[] encrypt(byte[] plainText) throws Exception {
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM));
        return cipher.doFinal(plainText);
    }

    /**
     * Decrypts given aes encrypted string to plain text.
     *
     * @param cipherText decryptable byte array
     */
    public String decrypt(byte[] cipherText) throws Exception {
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM));
        return new String(cipher.doFinal(cipherText));
    }
}
