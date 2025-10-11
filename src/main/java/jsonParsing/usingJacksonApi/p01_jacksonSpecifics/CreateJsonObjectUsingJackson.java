package jsonParsing.usingJacksonApi.p01_jacksonSpecifics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

public class CreateJsonObjectUsingJackson {


    @Test
    public void createSimpleJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "Ashish");
        objectNode.put("age", 26);

//        String jsonString = objectMapper.writeValueAsString(objectNode);
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
        System.out.println("createParentJson:\n"+jsonString);
    }

    @Test
    public void createParentJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode parameter = objectMapper.createObjectNode();
        parameter.put("name", "Ashish");
        parameter.put("age", 26);

        ObjectNode dataNode = objectMapper.createObjectNode();
        dataNode.set("data", parameter);

//        String jsonString = objectMapper.writeValueAsString(parameter);
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataNode);
        System.out.println("createParentJson:\n"+jsonString);
    }

    @Test
    public void createJsonArray() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNodeOne = objectMapper.createObjectNode();
        objectNodeOne.put("name", "Ashish");

        ObjectNode objectNodeTwo = objectMapper.createObjectNode();
        objectNodeTwo.put("name", "Kishor");

        ArrayNode jsonArray = objectMapper.createArrayNode();
        jsonArray.add(objectNodeOne);
        jsonArray.add(objectNodeTwo);

        ObjectNode parentNode = objectMapper.createObjectNode();
        parentNode.set("entity", jsonArray);


        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentNode);
        System.out.println("createParentJson:\n"+jsonString);
    }

}
