package jsonParsing.usingFileByte;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonParsingUsingReadAllBytes {

    @Test
    public void fetchingJsonFile() throws IOException, ParseException {
        byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingJsonObject/regionData.json"));
        String fileContent = new String(file);
        System.out.println(fileContent);    // fileContent can be passed to ...given().body(fileContent).... since body take json as a string parameter

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileContent);
        JSONObject jsonObject = (JSONObject) obj;

        System.out.println("Region Name:"+jsonObject.get("Region"));
    }
}
