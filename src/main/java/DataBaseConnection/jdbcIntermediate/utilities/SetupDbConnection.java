package DataBaseConnection.jdbcIntermediate.utilities;


import DataBaseConnection.jdbcIntermediate.entity.DbConnectionEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SetupDbConnection {

    public String url;
    public String dbUsername;
    public String dbPassword;
    public String dbPort;
    public String dbname;
//    public Connection connection;
    public static final String DB_CONNECTION_FILE = System.getProperty("user.dir") + "/src/main/java/DataBaseConnection/jdbcIntermediate/resources/dbConnections.json";
    private static Map<String, HikariDataSource> dataSourceMap = new ConcurrentHashMap<>();

//    public SetupDbConnection(String dbUsername, String dbPassword, String dbPort, String dbname) {
//        this.dbUsername = dbUsername;
//        this.dbPassword = dbPassword;
//        this.dbPort = dbPort;
//        this.dbname = dbname;
//    }

    public String createConnectionUrl(String dbName, String dbType, String hostname, String dbPort, String dbUsername, String dbPassword) {
        url = "jdbc:" + dbType + "://" + hostname + ":" + dbPort + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
        System.out.println("URL:" + url);
        return url;
    }

    public String createConnectionUrl(String dbName, String dbType, String hostname, String dbPort) {
        url = "jdbc:" + dbType + "://" + hostname + ":" + dbPort + "/" + dbName;
        System.out.println("URL:" + url);
        return url;
    }

    // Method to get a connection to the database
    public Connection setUpConnection(String dbName, String dbType, String hostname, String dbPort, String dbUsername, String dbPassword) {
        Connection connection;
        String url = createConnectionUrl(dbName, dbType, hostname, dbPort, dbUsername, dbPassword);
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public void initializeDataSourcePool() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<DbConnectionEntity> DbConnectionEntityList = mapper.readValue(new File(DB_CONNECTION_FILE), new TypeReference<>() {
        });
        for (DbConnectionEntity dbConnectionEntity : DbConnectionEntityList) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(createConnectionUrl(dbConnectionEntity.getDbName(), dbConnectionEntity.getDbType(), dbConnectionEntity.getHostname(), dbConnectionEntity.getDbPort()));
            config.setUsername(dbConnectionEntity.getDbUsername());
            config.setPassword(dbConnectionEntity.getDbPassword());

            dataSourceMap.put(dbConnectionEntity.getDbName(), new HikariDataSource(config));
        }

    }


    // Close the connection and statement resources
    public void closeResources() {
        dataSourceMap.values().forEach(
                dataSource -> {
                    try {
                        if ( dataSource.getConnection()!= null && !dataSource.getConnection().isClosed()) {
                            dataSource.close();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static Connection getConnectionFromDataSource(String dbname) throws SQLException {
        return dataSourceMap.get(dbname).getConnection();
    }

}
