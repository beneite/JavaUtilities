package DataBaseConnection.FetchDataFromDbToPojo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static DataBaseConnection.FetchDataFromDbToPojo.DbConnectionUtil.closeDbConnection;
import static DataBaseConnection.FetchDataFromDbToPojo.DbConnectionUtil.executeQuery;

public class FetchDataFromDbAndStoreInPojo {

    @Test
    public void getDataFromDB() throws SQLException {
        new DbConnectionUtil("localhost", "3306", "dbCOnnection", "root", "Ashish@123");
        ResultSet result = executeQuery("select * from employeeTable;");
        while (result.next()) {
            System.out.println(result.getInt(1));
            System.out.println(result.getString(2));
            System.out.println(result.getString(3));
            System.out.println(result.getDate(4));
        }

    }

    @AfterTest
    public void cleanUp() {
        closeDbConnection();
    }

}
