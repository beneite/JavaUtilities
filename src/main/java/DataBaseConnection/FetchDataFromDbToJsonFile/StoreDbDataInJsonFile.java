package DataBaseConnection.FetchDataFromDbToJsonFile;

import DataBaseConnection.FetchDataFromDb.DbConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static DataBaseConnection.FetchDataFromDb.DbConnectionUtil.closeDbConnection;
import static DataBaseConnection.FetchDataFromDb.DbConnectionUtil.executeQuery;

public class StoreDbDataInJsonFile {

    @Test
    public void storeDbDataIntoJsonFile() throws SQLException, IOException {
        ArrayList<EmployeeDetails> empList = new ArrayList<>();

        new DbConnectionUtil("localhost", "3306", "dbCOnnection", "root", "Ashish@123");
        ResultSet resultSet = executeQuery("select * from employeeTable;");

        while(resultSet.next()){
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployeeId(resultSet.getInt(1));
            employeeDetails.setEmployeeName(resultSet.getString(2));
            employeeDetails.setEmployeeAddress(resultSet.getString(3));
            employeeDetails.setEmployeeDoj(resultSet.getDate(4));

            empList.add(employeeDetails);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        // file will be stored in the same root module location. open the file and see the json in arraylist.
        objectMapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/DataBaseConnection/FetchDataFromDbToJsonFile/dbToJson.json"),empList);
    }

    @AfterTest
    public void cleanUp() {
        closeDbConnection();
    }
}
