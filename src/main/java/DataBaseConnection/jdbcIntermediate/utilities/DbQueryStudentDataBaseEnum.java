package DataBaseConnection.jdbcIntermediate.utilities;

public class DbQueryStudentDataBaseEnum {

    public enum SqlQueries {
        SELECT_ALL_RECORD_FROM_TABLE_USERS("SELECT * FROM users;"),
        SELECT_WITH_WHERE_ID("SELECT * FROM `users` u WHERE u.`user id` = ?;"),
        SELECT_WITH_WHERE_EMAIL("SELECT * FROM users u WHERE u.email = ?;"),
        INSERT_INTO_WITH_ALL_VALUES("INSERT into users(`user id`, email, `first-name`, `last-name`) VALUES(?, ?, ?, ?);");

        private final String query;

        SqlQueries(String query) {
            this.query = query;
        }

        public String getQueryAsString(){
            return query;
        }
    }

}
