package de.thro.inf;

import de.thro.inf.Cryptography;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * Test class for testing cryptography methods.
 *
 * @author Thomas Meza on 17.06.2018.
 */
public class CryptographyTest {
    private static final Cryptography c = new Cryptography("NAiwns238ku566iq75Ah63hs");
    private static final byte[] encryptedData = {-115, -68, -122, -74, -72, 62, -65, -75, 121, 106, 127, -75, 32, -56, 108, -116};

    /**
     * Tests if encryption method encrypts clear text to expected data.
     */
    @Test
    public void encrypt() {
        Cryptography c = new Cryptography("NAiwns238ku566iq75Ah63hs");

        String clearText = "Testcase";
        byte[] encrypted = {};

        try {
            encrypted = c.encrypt(clearText.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(new String(encryptedData), new String(encrypted));
    }

    /**
     * Tests if decryption method decrypts encrypted data to expected clear text.
     */
    @Test
    public void decrypt() {
        try {
            String decryptedString = c.decrypt(encryptedData);
            Assert.assertEquals("Testcase", decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}