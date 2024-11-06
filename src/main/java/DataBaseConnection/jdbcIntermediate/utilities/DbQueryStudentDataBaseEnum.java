package DataBaseConnection.jdbcIntermediate.utilities;

public class DbQueryStudentDataBaseEnum {

    public enum SqlQueries {
        SELECT_ALL_RECORD_FROM_TABLE_USERS("SELECT * FROM users;"),
        SELECT_WITH_WHERE_ID("SELECT * FROM `users` u WHERE u.`user id` = ?;"),
        SELECT_WITH_WHERE_EMAIL("SELECT * FROM users u WHERE u.email = ?;");

        private final String query;

        SqlQueries(String query) {
            this.query = query;
        }

        public String getQueryAsString(){
            return query;
        }
    }

}
