/**
 * refer the regionData.json file to get the Json structure.
 */
package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class RegionInformationData {

    @JsonProperty("description")
    private String description;

    @JsonProperty("region")
    private String region;

    @JsonProperty("countries")
    private List<CountriesData> countries;

    public RegionInformationData(){
        super();
    }

    public RegionInformationData(String description, String region, List<CountriesData> countries) {
        this.description = description;
        this.region = region;
        this.countries = countries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<CountriesData> getCountries() {
        return countries;
    }

    public void setCountries(List<CountriesData> countries) {
        this.countries = countries;
    }

    // Override toString() to print object as JSON
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
