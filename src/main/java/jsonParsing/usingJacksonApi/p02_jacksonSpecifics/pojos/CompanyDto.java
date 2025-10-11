package jsonParsing.usingJacksonApi.p02_jacksonSpecifics.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CompanyDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("catchPhrase")
    private String catchPhrase;

    @JsonProperty("bs")
    private String bs;
}
