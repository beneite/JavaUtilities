package DataBaseConnection.mySql_db_on_docker.db_operations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class getTheRecordsFromDb {

    public Connection connection;
    public SetupDbConnection setupDbConnection;


    @DataProvider(name = "studentDbData")
    public Object[][] returnDataForDb(){
        return new Object[][]{
                {"ashish", "ashish@123", "3307", "students_db"}
        };
    }

    @Test(description = "this will execute a script and print db data",
            dataProvider = "studentDbData"
    )
    public void getRecordsFromDbTest(String dbUsername, String dbPassword, String dbPort, String dbName) throws SQLException {
        setupDbConnection = new SetupDbConnection(dbUsername, dbPassword, dbPort, dbName);

        connection = setupDbConnection.setUpConnection(dbUsername, dbPassword, dbPort, dbName);
        String query = QueryEnums.SqlQueries.SELECT_ALL_FROM_TABLE_STUDENT_RECORD.getQuery();
        ResultSet resultSet = setupDbConnection.executeQuery(query);

        System.out.println(setupDbConnection.printResultSetData(resultSet));
    }
}
