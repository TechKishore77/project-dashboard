package qa.ade.project_dashboard.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

public class MySqlExceptionTranslator extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        switch (sqlEx.getErrorCode()) {
            case 1062:
                return new DuplicateKeyException("Unique key constraint violation");
            case 1146:
                return new TableDoesNotExistException("Failed to execute query. Table does not exist.");
        }
        System.out.println("Error Code: " + sqlEx.getErrorCode());
        sqlEx.printStackTrace();
        return super.customTranslate(task, sql, sqlEx);
    }
}
