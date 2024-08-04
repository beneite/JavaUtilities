/**
 * problem statement: suppose you have api response in json but as a string. your task to Deserialize it. Suppose regionData.json contains the response.
 */
package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertJsonResponseIntoJavaObjectTest {

    /**
     * here we will try to see the response converting into JavaObject
     */
    @Test
    public void jsonStringToJavaObject() throws IOException {
        String response = readFileDataAndReturnString(Paths.get(System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingJacksonApi/regionData.json"));

        // using object mapper to convert the response into RegionInformationData.java
        ObjectMapper objectMapper = new ObjectMapper();
        RegionInformationData regionInformationData = objectMapper.readValue(response, RegionInformationData.class);

        // printing the data to check the deserialization process.
        System.out.println("Description:"+regionInformationData.getDescription());
        System.out.println("Region:"+regionInformationData.getRegion());

    }

    /**
     * This method will read a file and convert the data into String and return
     * @param path path
     * @return file content in String
     * @throws IOException
     */
    public static String readFileDataAndReturnString(Path path) throws IOException {
        byte[] file = Files.readAllBytes(path);
        String fileContent = new String(file);
        return fileContent;
    }
}
