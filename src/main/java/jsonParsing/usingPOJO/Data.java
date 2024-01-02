package jsonParsing.usingPOJO;

public class Data {

    private String capital;
    private int mintemp;
    private int maxtemp;
    private String currency;
    private int population;

    public Data(String capital, int mintemp, int maxtemp, String currency, int population) {
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
