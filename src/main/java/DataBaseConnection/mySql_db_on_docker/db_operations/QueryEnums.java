package DataBaseConnection.mySql_db_on_docker.db_operations;

public class QueryEnums {

    public enum SqlQueries {
        SELECT_ALL_FROM_TABLE_STUDENT_RECORD("SELECT * FROM student_record");
        private final String query;
        SqlQueries(String query) {
            this.query = query;
        }

        public String getQuery(){
            return query;
        }
    }

}
