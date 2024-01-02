/**
 * refer the regionData.json file to get the Json structure.
 */
package jsonParsing.usingPOJO;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

    @Test
    public void serializationDemo(){

        //#1. Creating object for data
        Data dataNewDelhi = new Data("New Delhi",6,45,"Rupee",120);
        Data dataKatmandu = new Data("Kathmandu",8,40,"Nepalese rupee",50);
        Data dataSriLanka = new Data("Colombo",10,38,"Sri Lakan rupee",80);
        Data dataMaldives = new Data("Male",16,48,"Rupaye",15);

        //#2. adding different country data
        Countries countryIndia = new Countries("India",dataNewDelhi);
        Countries countryNepal = new Countries("Nepal",dataKatmandu);
        Countries countrySriLanka = new Countries("Sri lanka",dataSriLanka);
        Countries countryMaldives = new Countries("Maldives",dataMaldives);
        List<Countries> countryList = new ArrayList<>();
        countryList.add(countryIndia);
        countryList.add(countryNepal);
        countryList.add(countrySriLanka);
        countryList.add(countryMaldives);

        RegionInformation regionInformation = new RegionInformation("Map containing Country, capital, currency, and some States of that Country","Asia",countryList);
        System.out.println("Proper JSON format(via toStrings override):"+regionInformation);

        System.out.println("Without beautifying:"+new Gson().toJson(regionInformation));
    }
}
