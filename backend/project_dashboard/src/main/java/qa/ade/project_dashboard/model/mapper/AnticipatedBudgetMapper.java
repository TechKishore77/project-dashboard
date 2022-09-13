package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.AnticipatedBudget;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnticipatedBudgetMapper implements RowMapper<AnticipatedBudget> {
    public AnticipatedBudget mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnticipatedBudget anticipatedBudget = new AnticipatedBudget();
        anticipatedBudget.setId(rs.getLong("id"));
        anticipatedBudget.setTotalBudget(rs.getBigDecimal("total_budget"));
        anticipatedBudget.setDataDate(rs.getDate("data_date"));
        return anticipatedBudget;
    }
}
