package jsonParsing.usingJacksonApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootData {


    @JsonProperty("capital")
    private String capital;

    @JsonProperty("mintemp")
    private int mintemp;

    @JsonProperty("maxtemp")
    private int maxtemp;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("population")
    private int population;

    public RootData(){
        super();
    }

    public RootData(String capital, int mintemp, int maxtemp, String currency, int population) {
        this.capital = capital;
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
        this.currency = currency;
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getMintemp() {
        return mintemp;
    }

    public void setMintemp(int mintemp) {
        this.mintemp = mintemp;
    }

    public int getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(int maxtemp) {
        this.maxtemp = maxtemp;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
