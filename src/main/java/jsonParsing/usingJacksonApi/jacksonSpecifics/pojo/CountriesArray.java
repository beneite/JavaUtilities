package jsonParsing.usingJacksonApi.jacksonSpecifics.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountriesArray {

    @JsonProperty("countries")
    private List<CountryIndex> countries;

}
