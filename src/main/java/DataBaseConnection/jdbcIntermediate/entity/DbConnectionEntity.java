package DataBaseConnection.jdbcIntermediate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbConnectionEntity {

    private String  dbName;
    private String  dbType;
    private String  dbUsername;
    private String  dbPassword;
    private String  hostname;
    private String  dbPort;

}
