package jsonParsing.using_org_json;

import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConvertJsonStringToJavaObjectTest {

    @Test
    public void convertStringToJson() throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/main/java/jsonParsing/using_org_json/fullPayload.json"));
        String jsonString = new String(file);

        JSONObject jsonObject = new JSONObject(jsonString);

        System.out.println("noOfRecords:"+jsonObject.get("noOfRecords".toString()));
        System.out.println("Print first employee name:"+jsonObject.getJSONArray("data").getJSONObject(0).get("employeeName"));

    }
}
