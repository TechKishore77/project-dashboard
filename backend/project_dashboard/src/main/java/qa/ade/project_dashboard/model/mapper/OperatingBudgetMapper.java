package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.OperatingBudget;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatingBudgetMapper implements RowMapper<OperatingBudget> {
    public OperatingBudget mapRow(ResultSet rs, int rowNum) throws SQLException {
        OperatingBudget operatingBudget = new OperatingBudget();
        operatingBudget.setId(rs.getLong("id"));
        operatingBudget.setTotalBudget(rs.getBigDecimal("total_budget"));
        return operatingBudget;
    }
}
