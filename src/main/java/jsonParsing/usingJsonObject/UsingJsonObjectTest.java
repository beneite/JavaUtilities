/**
 * program to parse .json file and perform various types of operations...
 */
package jsonParsing.usingJsonObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class UsingJsonObjectTest {

    @Test
    public void usingJsonObject() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingJsonObject/testData.json");
        Object obj = parser.parse(file);
        JSONObject data = (JSONObject) obj;

        //#1. print the Region name
        System.out.println("Region Name:"+data.get("Region"));

        //#2. print the number of countries
        JSONArray jsonArray = (JSONArray) data.get("Countries");
        System.out.println("no of countries:"+jsonArray.size());

        //#3. print the countries name
        for(int i=0;i<jsonArray.size();i++){
            System.out.println("Country name:"+((JSONObject)jsonArray.get(i)).get("Country"));
        }

        //#4. print the total population including all countries
        long sum=0;
        for(int i=0;i<jsonArray.size();i++){
           long population = (long) ((JSONObject)((JSONObject)jsonArray.get(i)).get("Data")).get("population");
           sum=sum+population;
        }
        System.out.println("Total Population:"+sum);

        //#5. print the population of country: nepal
        long nepalPopulation=0;
        for(int i=0;i<jsonArray.size();i++){
            if(((JSONObject)jsonArray.get(i)).get("Country").toString().equalsIgnoreCase("Nepal")){
                nepalPopulation = (long) ((JSONObject)((JSONObject)jsonArray.get(i)).get("Data")).get("population");
            }
        }
        System.out.println("Total Population of nepal:"+nepalPopulation);

        //#6. print the countries name using enhanced for loop
        for(Object o:jsonArray){
            System.out.println("Country name:"+((JSONObject)o).get("Country"));
        }
    }
}
