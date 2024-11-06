package DataBaseConnection.jdbcIntermediate.test;

import DataBaseConnection.jdbcIntermediate.utilities.SetupDbConnection;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.sql.SQLException;

public class BaseTest {

    SetupDbConnection setupDbConnection = new SetupDbConnection();

    @BeforeSuite
    public void beforeSuiteMethod() throws IOException {
        System.out.println("**** Starting BeforeSuite");
        setupDbConnection.initializeDataSourcePool();
    }

    @AfterSuite
    public void afterSuiteMethod() throws SQLException {
        System.out.println("**** Starting AfterSuite");
        setupDbConnection.closeResources();
    }

}
