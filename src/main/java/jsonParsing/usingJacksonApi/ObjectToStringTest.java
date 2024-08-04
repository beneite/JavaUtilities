package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ObjectToStringTest {

    @Test
    public void ConvertObjectToJsonString() throws JsonProcessingException {
        //#1. Creating object for data
        RootData rootDataNewDelhi = new RootData("New Delhi",6,45,"Rupee",120);
        RootData rootDataKatmandu = new RootData("Kathmandu",8,40,"Nepalese rupee",50);
        RootData rootDataSriLanka = new RootData("Colombo",10,38,"Sri Lakan rupee",80);
        RootData rootDataMaldives = new RootData("Male",16,48,"Rupaye",15);

        //#2. adding different country data
        CountriesData countryIndia = new CountriesData("India",rootDataNewDelhi);
        CountriesData countryNepal = new CountriesData("Nepal",rootDataKatmandu);
        CountriesData countrySriLanka = new CountriesData("Sri lanka",rootDataSriLanka);
        CountriesData countryMaldives = new CountriesData("Maldives",rootDataMaldives);
        List<CountriesData> countryList = new ArrayList<>();
        countryList.add(countryIndia);
        countryList.add(countryNepal);
        countryList.add(countrySriLanka);
        countryList.add(countryMaldives);

        RegionInformationData regionInformationData = new RegionInformationData("Map containing Country, capital, currency, and some States of that Country","Asia",countryList);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(regionInformationData);
        System.out.println("Object to Json String:"+json);
    }

}
