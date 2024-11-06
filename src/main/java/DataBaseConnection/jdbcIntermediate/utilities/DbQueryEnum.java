package DataBaseConnection.jdbcIntermediate.utilities;

public class DbQueryEnum {

    public enum SqlQueries {
        SELECT_ALL_RECORD_FROM_TABLE_EMPLOYEE("SELECT * FROM employeeTable;"),
        SELECT_ALL_RECORD_FROM_TABLE_USERS("SELECT * FROM users;");

        private final String query;

        SqlQueries(String query) {
            this.query = query;
        }

        public String getQueryAsString(){
            return query;
        }
    }

}
