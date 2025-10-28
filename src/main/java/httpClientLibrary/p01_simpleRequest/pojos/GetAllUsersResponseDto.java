package httpClientLibrary.p01_simpleRequest.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class GetAllUsersResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

}
