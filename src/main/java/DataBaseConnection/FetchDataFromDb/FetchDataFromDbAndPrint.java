package DataBaseConnection.FetchDataFromDb;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static DataBaseConnection.FetchDataFromDb.DbConnectionUtil.closeDbConnection;
import static DataBaseConnection.FetchDataFromDb.DbConnectionUtil.executeQuery;

public class FetchDataFromDbAndPrint {

    @Test
    public void printDataFromDB() throws SQLException {
        new DbConnectionUtil("localhost", "3306", "dbCOnnection", "root", "Ashish@123");
        ResultSet result = executeQuery("select * from employeeTable;");
        System.out.println("Printing the data from DB");
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
