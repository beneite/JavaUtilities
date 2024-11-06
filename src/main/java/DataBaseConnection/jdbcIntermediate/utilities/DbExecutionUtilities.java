package DataBaseConnection.jdbcIntermediate.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static ResultSet executePreparedStatementQuery(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        System.out.println("SQL query to be executed:"+preparedStatement.toString());
        return preparedStatement.executeQuery();
    }

    public static String printResultSetData(ResultSet resultSet) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnNumber = metaData.getColumnCount();

        // Define the width for each column (you can adjust this as needed)
        int columnWidth = 25; // Set to a fixed width that works for your data

        // Print the header row
        for (int i = 1; i <= columnNumber; i++) {
            String columnName = metaData.getColumnName(i);
            stringBuilder.append(String.format("%-" + columnWidth + "s", columnName)); // Left-align with width
        }
        stringBuilder.append("\n");

        // Print the separator line
        stringBuilder.append("-".repeat(columnWidth * columnNumber));
        stringBuilder.append("\n");

        // Print the data rows
        while (resultSet.next()) {
            for (int j = 1; j <= columnNumber; j++) {
                Object value = resultSet.getObject(j);
                String valueString = (value == null) ? "NULL" : value.toString();
                stringBuilder.append(String.format("%-" + columnWidth + "s", valueString)); // Left-align with width
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
