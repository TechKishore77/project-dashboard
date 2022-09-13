package qa.ade.project_dashboard.exceptions;

import org.springframework.dao.DataAccessException;

public class TableDoesNotExistException extends DataAccessException {
    public TableDoesNotExistException(String msg) {
        super(msg);
    }
}
