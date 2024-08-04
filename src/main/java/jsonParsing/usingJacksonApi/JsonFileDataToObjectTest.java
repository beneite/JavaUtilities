package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileDataToObjectTest {

    @Test
    public void convertingJsonToObject() throws IOException {

        byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingJacksonApi/regionData.json"));
        String fileContent = new String(file);
        ObjectMapper objectMapper = new ObjectMapper();
        RegionInformationData regionInformationData = objectMapper.readValue(fileContent,RegionInformationData.class);

        System.out.printf("description:"+regionInformationData.getDescription());
    }
}
