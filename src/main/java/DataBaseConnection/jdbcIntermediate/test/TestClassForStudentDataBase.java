package DataBaseConnection.jdbcIntermediate.test;

import DataBaseConnection.jdbcIntermediate.utilities.DbExecutionUtilities;
import DataBaseConnection.jdbcIntermediate.utilities.DbQueryEnum;
import DataBaseConnection.jdbcIntermediate.utilities.SetupDbConnection;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestClassForStudentDataBase extends BaseTest{

    @DataProvider(name = "employeeDbData")
    public Object[][] returnDataForDb(){
        return new Object[][]{
                {"studentDataBase"}
        };
    }

    @Test(description = "this will execute a script and print db data",
            dataProvider = "employeeDbData"
    )
    public void getRecordsFromStudentDbTest(String dbName) throws SQLException {
        Connection connection = SetupDbConnection.getConnectionFromDataSource(dbName);
        String query = DbQueryEnum.SqlQueries.SELECT_ALL_RECORD_FROM_TABLE_USERS.getQueryAsString();
        ResultSet resultSet = DbExecutionUtilities.executeQuery(connection, query);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));
    }
}
