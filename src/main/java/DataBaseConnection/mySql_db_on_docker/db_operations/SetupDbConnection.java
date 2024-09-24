package DataBaseConnection.mySql_db_on_docker.db_operations;


import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.AfterSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Getter
@Setter
public class SetupDbConnection {

    public static String url;
    public String dbUsername;
    public String dbPassword;
    public String dbPort;
    public String dbname;
    public Connection connection;

    public SetupDbConnection(String dbUsername, String dbPassword, String dbPort, String dbname) {
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbPort = dbPort;
        this.dbname = dbname;
    }

    public static String createConnectionUrl(String dbUsername, String dbPassword, String dbPort, String dbName){
        url = "jdbc:mysql://" + "127.0.0.1" + ":" + dbPort + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
        System.out.println("URL:"+url);
        return url;
    }

    // Method to get a connection to the database
    public Connection setUpConnection(String dbUsername, String dbPassword, String dbPort, String dbName) throws SQLException {
        String url = createConnectionUrl(dbUsername, dbPassword, dbPort, dbName);
        connection = DriverManager.getConnection(url);
        setConnection(connection);
        return connection;
    }

    public ResultSet executeQuery(String sqlQuery) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement statement = getConnection().createStatement();
        return statement.executeQuery(sqlQuery);
    }

    /**
     * For INSERT, UPDATE, and DELETE operations, you should use executeUpdate() instead of executeQuery(), as executeQuery() is designed to return a ResultSet,
     * which makes sense only for queries that retrieve data.
     * @param sqlQuery sqlQuery
     * @throws SQLException
     */
    public void executeUpdateQuery(String sqlQuery) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(sqlQuery);
    }


    @AfterSuite(alwaysRun = true)
    // Close the connection and statement resources
    public void closeResources() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String printResultSetData(ResultSet resultSet) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnNumber = metaData.getColumnCount();
        while(resultSet.next()){
            for(int j=1; j <= columnNumber; j++){
                // Get column name and value
                String columnName = metaData.getColumnName(j);
                Object value = resultSet.getObject(j);
                stringBuilder.append(columnName).append(": ").append(value).append(", ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
