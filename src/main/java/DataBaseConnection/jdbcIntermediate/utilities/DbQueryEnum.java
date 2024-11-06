package DataBaseConnection.jdbcIntermediate.utilities;

public class DbQueryEnum {

    public enum SqlQueries {
        SELECT_ALL_RECORD_FROM_TABLE("SELECT * FROM employeeTable;");

        private final String query;

        SqlQueries(String query) {
            this.query = query;
        }

        public String getQueryAsString(){
            return query;
        }
    }

}
