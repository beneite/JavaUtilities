package jsonParsing.usingJacksonApi.jacksonSpecifics.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CountryParameters {

    private String capital;
    private Long mintemp;
    private Long maxtemp;
    private Long currency;
    private Long population;
}
