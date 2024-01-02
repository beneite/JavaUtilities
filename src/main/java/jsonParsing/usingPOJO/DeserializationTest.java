/**
 * refer the regionData.json file to get the Json structure.
 */
package jsonParsing.usingPOJO;

import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeserializationTest {

    @Test
    public void deSerializationDemo() throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingPOJO/regionData.json"));
        String fileContent = new String(file);

        RegionInformation regionInformation = new Gson().fromJson(fileContent, RegionInformation.class);

        //#1. print the Region name
        System.out.println("Region:"+regionInformation.getRegion());

        //#2. print the Description name
        System.out.println("description:"+regionInformation.getDescription());

        //#print the capital of India
        for(int i=0;i<regionInformation.getCountries().size();i++){
            if(regionInformation.getCountries().get(i).getCountry().equalsIgnoreCase("SriLanka")){
                System.out.println("Capital of SriLanda:"+regionInformation.getCountries().get(i).getData().getCapital());
            }
        }


    }
}
