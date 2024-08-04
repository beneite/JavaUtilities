/**
 * this class basically defined the json file structure.
 */
package DataBaseConnection.FetchDataFromDbToJsonFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class JsonFileContent {

    private ArrayList<EmployeeDetails> data;
    private Integer noOfRecords;
}
