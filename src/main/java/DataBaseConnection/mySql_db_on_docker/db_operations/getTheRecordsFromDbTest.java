package DataBaseConnection.mySql_db_on_docker.db_operations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class getTheRecordsFromDbTest {

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

    @Test(description = "Add a new record in the DB", dataProvider = "studentDbData")
    public void addRecordInDbTest(String dbUsername, String dbPassword, String dbPort, String dbName) throws SQLException {
        setupDbConnection = new SetupDbConnection(dbUsername, dbPassword, dbPort, dbName);

        connection = setupDbConnection.setUpConnection(dbUsername, dbPassword, dbPort, dbName);

        setupDbConnection.executeUpdateQuery("INSERT INTO student_record (id, name, doj) VALUES(4, 'MUKESH', '16/09/1995');");

        String query = QueryEnums.SqlQueries.SELECT_ALL_FROM_TABLE_STUDENT_RECORD.getQuery();
        ResultSet resultSet = setupDbConnection.executeQuery(query);

        System.out.println(setupDbConnection.printResultSetData(resultSet));

    }

    @Test(description = "update a student record", dataProvider = "studentDbData")
    public void updateRecordInDbTest(String dbUsername, String dbPassword, String dbPort, String dbName) throws SQLException {
        setupDbConnection = new SetupDbConnection(dbUsername, dbPassword, dbPort, dbName);

        connection = setupDbConnection.setUpConnection(dbUsername, dbPassword, dbPort, dbName);
        String query = QueryEnums.SqlQueries.SELECT_ALL_FROM_TABLE_STUDENT_RECORD.getQuery();
        ResultSet resultSet = setupDbConnection.executeQuery(query);
        System.out.println("Before update:"+setupDbConnection.printResultSetData(resultSet));

        String updateQuery = "UPDATE student_record SET name = 'updatedName' where id = '2';";
        setupDbConnection.executeUpdateQuery(updateQuery);


        resultSet = setupDbConnection.executeQuery(query);
        System.out.println("After update:"+setupDbConnection.printResultSetData(resultSet));
    }


}
