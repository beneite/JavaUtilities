package jsonParsing.usingJacksonApi.jacksonSpecifics.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {

    private String country;

    @JsonProperty("data")
    private Data data;

}
