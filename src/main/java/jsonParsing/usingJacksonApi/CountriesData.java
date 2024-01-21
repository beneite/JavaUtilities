/**
 * refer the regionData.json file to get the Json structure.
 */
package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountriesData {

    @JsonProperty("country")
    private String country;

    @JsonProperty("data")
    private RootData data;

    public CountriesData(){
        super();
    }

    public CountriesData(String country, RootData data) {
        this.country = country;
        this.data = data;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RootData getData() {
        return data;
    }

    public void setData(RootData data) {
        this.data = data;
    }
}
