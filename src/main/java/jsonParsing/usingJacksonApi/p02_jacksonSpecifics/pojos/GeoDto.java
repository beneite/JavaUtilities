package jsonParsing.usingJacksonApi.p02_jacksonSpecifics.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoDto {

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lng")
    private String lng;
}
