package DataBaseConnection.mySqlDb.FetchDataFromDbToJsonFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDetails {

    private Integer employeeId;
    private String employeeName;
    private String employeeAddress;
    private Date employeeDoj;
}
