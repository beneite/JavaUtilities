/**
 * refer the regionData.json file to get the Json structure.
 */
package jsonParsing.usingPOJO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class RegionInformation {

    private String description;
    private String region;
    private List<Countries> countries;

    public RegionInformation(String description, String region, List<Countries> countries) {
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

    public List<Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }

    // Override toString() to print object as JSON
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
