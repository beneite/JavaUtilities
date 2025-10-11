package jsonParsing.usingJacksonApi.jacksonSpecifics;

import com.fasterxml.jackson.databind.ObjectMapper;
import jsonParsing.usingJacksonApi.jacksonSpecifics.pojo.Country;
import jsonParsing.usingJacksonApi.jacksonSpecifics.pojo.ParentJson;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadDataAsPojo {

    @Test
    public void readDataFromJsonFile() throws IOException {

        byte[] file = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingJacksonApi/regionData.json"));
        String fileContent = new String(file);

        ObjectMapper objectMapper = new ObjectMapper();
        ParentJson parentJson = objectMapper.readValue(fileContent, ParentJson.class);

        System.out.println("Description: "+parentJson.getDescription());
        System.out.println("Region: "+parentJson.getRegion());

        for (Country country : parentJson.getCountries()){
            System.out.println("\nCountry: " + country.getCountry());
            System.out.println("  Capital: " + country.getData().getCapital());
            System.out.println("  Min Temp: " + country.getData().getMintemp());
            System.out.println("  Max Temp: " + country.getData().getMaxtemp());
            System.out.println("  Currency: " + country.getData().getCurrency());
            System.out.println("  Population: " + country.getData().getPopulation());
        }
    }

}
