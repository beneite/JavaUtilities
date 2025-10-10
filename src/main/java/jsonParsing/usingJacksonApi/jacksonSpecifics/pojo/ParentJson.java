package jsonParsing.usingJacksonApi.jacksonSpecifics.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ParentJson {

    private String description;
    private String region;

    @JsonProperty("countries")
    private CountriesArray countriesArray;
}
