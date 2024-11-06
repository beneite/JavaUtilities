package DataBaseConnection.jdbcIntermediate.test;

import DataBaseConnection.jdbcIntermediate.utilities.DbExecutionUtilities;
import DataBaseConnection.jdbcIntermediate.utilities.DbQueryEnum;
import DataBaseConnection.jdbcIntermediate.utilities.SetupDbConnection;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestClassForEmployee extends BaseTest{


    @DataProvider(name = "studentDbData")
    public Object[][] returnDataForDb(){
        return new Object[][]{
                {"dbCOnnection"}
        };
    }

    @Test(description = "this will execute a script and print db data",
            dataProvider = "studentDbData"
    )
    public void getRecordsFromDbTest(String dbName) throws SQLException {
        Connection connection = SetupDbConnection.getConnectionFromDataSource(dbName);
        String query = DbQueryEnum.SqlQueries.SELECT_ALL_RECORD_FROM_TABLE.getQueryAsString();
        ResultSet resultSet = DbExecutionUtilities.executeQuery(connection, query);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));
    }
}
