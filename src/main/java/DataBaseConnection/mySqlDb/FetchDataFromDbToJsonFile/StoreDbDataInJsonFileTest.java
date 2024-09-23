package DataBaseConnection.mySqlDb.FetchDataFromDbToJsonFile;

import DataBaseConnection.mySqlDb.FetchDataFromDb.DbConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static DataBaseConnection.mySqlDb.FetchDataFromDb.DbConnectionUtil.closeDbConnection;
import static DataBaseConnection.mySqlDb.FetchDataFromDb.DbConnectionUtil.executeQuery;

public class StoreDbDataInJsonFileTest {

    /**
     * This method will put the sql query data into json file along with the no of records.
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void storeDbDataIntoJsonFile() throws SQLException, IOException {
        ArrayList<EmployeeDetails> empList = new ArrayList<>();         // empList will be used to store all the query results
        JsonFileContent jsonFileContent = new JsonFileContent();        //
        jsonFileContent.setData(empList);

        // calling constructor to initialise the DB connection
        new DbConnectionUtil("localhost", "3306", "dbCOnnection", "root", "Ashish@123");
        ResultSet resultSet = executeQuery("select * from employeeTable Limit 3;");

        int i=0;
        while(resultSet.next()){
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployeeId(resultSet.getInt(1));
            employeeDetails.setEmployeeName(resultSet.getString(2));
            employeeDetails.setEmployeeAddress(resultSet.getString(3));
            employeeDetails.setEmployeeDoj(resultSet.getDate(4));

            empList.add(employeeDetails);
            i++;
        }
        jsonFileContent.setNoOfRecords(i);      // setting the no of records in data
        ObjectMapper objectMapper = new ObjectMapper();
        // file will be stored in the same root module location
        objectMapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/DataBaseConnection/mySqlDb/FetchDataFromDbToJsonFile/dbToJson.json"), jsonFileContent);
    }

    @AfterTest
    public void cleanUp() {
        closeDbConnection();
    }
}
