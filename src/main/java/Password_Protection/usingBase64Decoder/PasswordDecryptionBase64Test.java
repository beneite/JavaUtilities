/**
 * here we will demonstrate how to store password in your automation code.
 */
package Password_Protection.usingBase64Decoder;

import com.mysql.cj.util.Base64Decoder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class PasswordDecryptionBase64Test {

    public static Properties properties;

    /**
     * Test case flow:
     * 1. Encrypted password will be stored in your credentials.properties file.
     * 2. We will load the properties file.
     * 3. read the Encrypted password then use it in our Automation
     */
    @Test
    public void protectMyPassword() {

        String encryptedPassword = properties.getProperty("password");
        System.out.println("Actual password:"+decryptThePassword(encryptedPassword));

    }

    @BeforeTest
    public void readPropertiesFile() {
        properties = new Properties();
        String filePath = System.getProperty("user.dir") + "/src/main/java/Password_Protection/usingBase64Decoder/credentials.properties";

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptThePassword(String encryptedPassword){

        byte[] bytePassword = Base64.getDecoder().decode(encryptedPassword.getBytes());
        return new String(bytePassword);
    }


}
