/**
 * program to create Json during run ttime.
 */
package jsonParsing.usingJsonObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RunTimeJson {
    public static void main(String[] args) throws ParseException {

        JSONObject customJson = new JSONObject();
        customJson.put("Description","Map containing Country, Capital, Currency, and some States of that Country");
        customJson.put("Region","Asia");

        //#1. printing the json
        System.out.println(customJson.toString());

        //#2. convert string to Json.
        String str = "{\n" +
                "      \"Data\": {\n" +
                "        \"Capital\": \"New Delhi\",\n" +
                "        \"mintemp\": 6,\n" +
                "        \"maxtemp\": 45,\n" +
                "        \"Currency\": \"Rupee\",\n" +
                "        \"population\": 120\n" +
                "      }\n" +
                "    }";

        JSONParser parser = new JSONParser();
        JSONObject stringToJson = (JSONObject) parser.parse(str);
        System.out.printf("String to Json:"+stringToJson.toString());

        //#3.create the same json as present in src/main/java/jsonParsing/usingJsonObject/regionData.json

    }
}
