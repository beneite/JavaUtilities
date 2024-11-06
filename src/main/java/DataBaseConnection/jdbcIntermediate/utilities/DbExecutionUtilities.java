package DataBaseConnection.jdbcIntermediate.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DbExecutionUtilities {

    public static ResultSet executeQuery(Connection connection, String sqlQuery) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }

    /**
     * For INSERT, UPDATE, and DELETE operations, you should use executeUpdate() instead of executeQuery(), as executeQuery() is designed to return a ResultSet,
     * which makes sense only for queries that retrieve data.
     * @param sqlQuery sqlQuery
     * @throws SQLException
     */
    public static void executeUpdateQuery(Connection connection, String sqlQuery) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
    }

    public static String printResultSetData(ResultSet resultSet) throws SQLException {
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
