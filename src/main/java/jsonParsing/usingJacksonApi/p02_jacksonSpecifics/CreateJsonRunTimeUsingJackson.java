package jsonParsing.usingJacksonApi.p02_jacksonSpecifics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

public class CreateJsonRunTimeUsingJackson {

    @Test
    public void createJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode parentNode = objectMapper.createObjectNode();
        ObjectNode addressNode = objectMapper.createObjectNode();
        ObjectNode geoNode = objectMapper.createObjectNode();
        ObjectNode companyNode = objectMapper.createObjectNode();

        parentNode.put("id", 1);
        parentNode.put("name", "Leanne Graham");
        parentNode.put("username", "Bret");
        parentNode.put("email", "Sincere@april.biz");

        addressNode.put("street", "Kulas Light");
        addressNode.put("suite", "Apt. 556");
        addressNode.put("city", "Gwenborough");
        addressNode.put("zipcode", "92998-3874");

        geoNode.put("lat", "-37.3159");
        geoNode.put("lng", "81.1496");

        addressNode.set("geo", geoNode);
        parentNode.set("address", addressNode);

        companyNode.put("name", "Romaguera-Crona");
        companyNode.put("catchPhrase", "Multi-layered client-server neural-net");
        companyNode.put("bs", "harness real-time e-markets");

        parentNode.put("phone", "1-770-736-8031 x56442");
        parentNode.put("website", "hildegard.org");

        parentNode.set("company", companyNode);

        arrayNode.add(parentNode);

        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
        System.out.println("jsonStr:\n"+jsonStr);
    }
}
