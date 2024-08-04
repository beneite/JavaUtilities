package DataBaseConnection.FetchDataFromDbToPojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionUtil {

    private static String dbUrl;

    private static Connection dbConnection;

    /**
     * method to form a connection URL and send it to establshSqlDbConnection method.
     * @param hostname hostname
     * @param dbUserName dbUserName
     * @param dbPassword dbPassword
     * @param dbName dbName
     * @param port port
     */
    public DbConnectionUtil(String hostname, String port, String dbName, String dbUserName, String dbPassword){
        dbUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;
        if(dbConnection==null)
            dbConnection = establshSqlDbConnection();
    }

    /**
     * method to establish the SQL db connection
     * @return Connection
     */
    public static Connection establshSqlDbConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection(dbUrl);
            System.out.println("Db Connection is successful - "+dbUrl);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException ex ) {
            System.out.println("Error while connecting to Database " + ex.getMessage());
        }
        return dbConnection ;
    }

    public Connection getDbConnection(){
        return dbConnection;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * method to close the dbConnection
     */
    public static void closeDbConnection(){
        if(dbConnection != null){
            try {
                dbConnection.close();
                System.out.println("DB connection closed");
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * method to return ResultSet after query execution
     * @param query sql-query
     * @return ResultSet
     */
    public static ResultSet executeQuery(String query) {
        Statement statement = null;
        ResultSet queryResultSet = null;
        try {
            statement = dbConnection.createStatement();
            queryResultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return queryResultSet;
    }
}
