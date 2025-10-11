package jsonParsing.usingJacksonApi.p01_jacksonSpecifics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

public class HybridJsonCreation {

    @Test
    public void createHybridJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode parentNode = objectMapper.createObjectNode();
        ArrayNode countriesArray = objectMapper.createArrayNode();
        ObjectNode countryNameIndia = objectMapper.createObjectNode();
        ObjectNode countryParametersIndia = objectMapper.createObjectNode();

        countryParametersIndia.put("capital", "New Delhi");
        countryParametersIndia.put("mintemp", 6);
        countryParametersIndia.put("maxtemp", 45);
        countryParametersIndia.put("currency", "Rupee");
        countryParametersIndia.put("population", 120);

        countryNameIndia.put("country", "India");
        countryNameIndia.set("data", countryParametersIndia);

        countriesArray.add(countryNameIndia);


        ObjectNode countryNameNepal = objectMapper.createObjectNode();
        ObjectNode countryParametersNepal = objectMapper.createObjectNode();
        countryParametersNepal.put("capital", "Kathmandu");
        countryParametersNepal.put("mintemp", 8);
        countryParametersNepal.put("maxtemp", 40);
        countryParametersNepal.put("currency", "Nepalese rupee");
        countryParametersNepal.put("population", 50);

        countryNameNepal.put("country", "Nepal");
        countryNameNepal.set("data", countryParametersNepal);

        countriesArray.add(countryNameNepal);

        parentNode.put("description", "Map containing Country, capital, currency, and some States of that Country");
        parentNode.put("region", "Asia");
        parentNode.set("countries", countriesArray);

        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentNode);
        System.out.println("createParentJson:\n"+jsonString);
    }

}
