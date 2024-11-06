package DataBaseConnection.jdbcIntermediate.test;

import DataBaseConnection.jdbcIntermediate.utilities.DbExecutionUtilities;
import DataBaseConnection.jdbcIntermediate.utilities.DbQueryStudentDataBaseEnum;
import DataBaseConnection.jdbcIntermediate.utilities.SetupDbConnection;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String query = DbQueryStudentDataBaseEnum.SqlQueries.SELECT_ALL_RECORD_FROM_TABLE_USERS.getQueryAsString();
        ResultSet resultSet = DbExecutionUtilities.executeQuery(connection, query);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));
    }

    @Test(description = "this will execute a script and print db data using prepared statements and search via id",
            dataProvider = "employeeDbData"
    )
    public void getRecordsFromStudentDbByPreparedStatementTest(String dbName) throws SQLException {
        Connection connection = SetupDbConnection.getConnectionFromDataSource(dbName);
        String query = DbQueryStudentDataBaseEnum.SqlQueries.SELECT_WITH_WHERE_ID.getQueryAsString();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 1);

        ResultSet resultSet = DbExecutionUtilities.executePreparedStatementQuery(connection, preparedStatement);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));
    }

    @Test(description = "this will execute a script and print db data using prepared statements and search via email",
            dataProvider = "employeeDbData"
    )
    public void getRecordsFromStudentDbByPreparedStatementByEmailTest(String dbName) throws SQLException {
        Connection connection = SetupDbConnection.getConnectionFromDataSource(dbName);
        String query = DbQueryStudentDataBaseEnum.SqlQueries.SELECT_WITH_WHERE_EMAIL.getQueryAsString();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "Umang.Kamal@yopmail.com");

        ResultSet resultSet = DbExecutionUtilities.executePreparedStatementQuery(connection, preparedStatement);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));
    }

    @Test(description = "this will execute a script and print db data using prepared statements and search via email",
            dataProvider = "employeeDbData"
    )
    public void insertRecordUsingPreparedStatementsTest(String dbName) throws SQLException {
        Connection connection = SetupDbConnection.getConnectionFromDataSource(dbName);
        String query = DbQueryStudentDataBaseEnum.SqlQueries.INSERT_INTO_WITH_ALL_VALUES.getQueryAsString();

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, 6);
        preparedStatement.setString(2, "DE.FR@yopmail.com");
        preparedStatement.setString(3, "DE");
        preparedStatement.setString(4, "FR");

        int rowsAffected = DbExecutionUtilities.executePreparedStatementUpdateQuery(connection, preparedStatement);

        System.out.println("rowsAffected:"+rowsAffected);

        String selectAllQuery = DbQueryStudentDataBaseEnum.SqlQueries.SELECT_ALL_RECORD_FROM_TABLE_USERS.getQueryAsString();
        ResultSet resultSet = DbExecutionUtilities.executeQuery(connection, selectAllQuery);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));
    }

    @Test(description = "this will execute a script and print db data using prepared statements and search via email",
            dataProvider = "employeeDbData"
    )
    public void AutoCommitTest(String dbName) throws SQLException {

        Connection connection = SetupDbConnection.getConnectionFromDataSource(dbName);

        connection.setAutoCommit(false);        // no data will be saved to db since auto commit is false
        String query = DbQueryStudentDataBaseEnum.SqlQueries.INSERT_INTO_WITH_ALL_VALUES.getQueryAsString();

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, 7);
        preparedStatement.setString(2, "GT.HY@yopmail.com");
        preparedStatement.setString(3, "GT");
        preparedStatement.setString(4, "HY");

        int rowsAffected = DbExecutionUtilities.executePreparedStatementUpdateQuery(connection, preparedStatement);

        System.out.println("rowsAffected:"+rowsAffected);

        String selectAllQuery = DbQueryStudentDataBaseEnum.SqlQueries.SELECT_ALL_RECORD_FROM_TABLE_USERS.getQueryAsString();
        ResultSet resultSet = DbExecutionUtilities.executeQuery(connection, selectAllQuery);

        System.out.println(DbExecutionUtilities.printResultSetData(resultSet));

        // we also have connection.commit(); to commit the transaction
        // we also have connection.rollback(); to rollback the transaction in case of any error (note: committed transaction cannot be rolled back)
    }
}
