package DataBaseConnection.jdbcIntermediate.utilities;

public class DbQuerydbCOnnectionEnum {

    public enum SqlQueries {
        SELECT_ALL_RECORD_FROM_TABLE_EMPLOYEE("SELECT * FROM employeeTable;");

        private final String query;

        SqlQueries(String query) {
            this.query = query;
        }

        public String getQueryAsString(){
            return query;
        }
    }

}