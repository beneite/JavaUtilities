/**
 * refer the regionData.json file to get the Json structure.
 */
package jsonParsing.usingPOJO;

public class Countries {

    private String country;
    private Data data;

    public Countries(String country, Data data) {
        this.country = country;
        this.data = data;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
