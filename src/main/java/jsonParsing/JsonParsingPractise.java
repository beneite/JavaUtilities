/**
 * this java file is consuming data from /src/main/java/jsonParsing/testdata.json file
 */
package jsonParsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class JsonParsingPractise {

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/src/main/java/jsonParsing/testdata.json"));

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
        int sum=0;
        for(int i=0;i<jsonArray.size();i++){

            // int population =
            sum=sum+population;
        }
        System.out.println("Population:"+sum);

    }
}
