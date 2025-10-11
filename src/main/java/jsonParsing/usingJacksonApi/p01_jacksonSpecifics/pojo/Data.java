package jsonParsing.usingJacksonApi.p01_jacksonSpecifics.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Data {

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
}
