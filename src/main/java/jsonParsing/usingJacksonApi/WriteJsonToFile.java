package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WriteJsonToFile {

    @Test
    public void writeJson() throws IOException {
        //#1. Creating object for data
        RootData rootDataNewDelhi = new RootData("New Delhi",6,45,"Rupee",120);
        RootData rootDataKatmandu = new RootData("Kathmandu",8,40,"Nepalese rupee",50);
        RootData rootDataSriLanka = new RootData("Colombo",10,38,"Sri Lakan rupee",80);
        RootData rootDataMaldives = new RootData("Male",16,48,"Rupaye",15);
        RootData rootDataDenmark = new RootData("Swiss",2,15,"Euro",15);

        //#2. adding different country data
        CountriesData countryIndia = new CountriesData("India",rootDataNewDelhi);
        CountriesData countryNepal = new CountriesData("Nepal",rootDataKatmandu);
        CountriesData countrySriLanka = new CountriesData("Sri lanka",rootDataSriLanka);
        CountriesData countryMaldives = new CountriesData("Maldives",rootDataMaldives);
        CountriesData countryDenmark = new CountriesData("Denmark",rootDataDenmark);
        List<CountriesData> countryList = new ArrayList<>();
        countryList.add(countryIndia);
        countryList.add(countryNepal);
        countryList.add(countrySriLanka);
        countryList.add(countryMaldives);
        countryList.add(countryDenmark);

        RegionInformationData regionInformationData = new RegionInformationData("Map containing Country, capital, currency, and some States of that Country","Asia",countryList);

        ObjectMapper objectMapper = new ObjectMapper();

        // File Path:
        String outputFilePath = System.getProperty("user.dir")+"/src/main/java/jsonParsing/usingJacksonApi/RegionInformationData_"+returnCurrentDate()+".json";

        // Writing value in to new file
        objectMapper.writeValue(new File(outputFilePath),regionInformationData);
    }

    public static String returnCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
