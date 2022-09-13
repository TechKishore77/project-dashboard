package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.OperatingBudgetComponent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatingBudgetComponentMapper implements RowMapper<OperatingBudgetComponent> {
    public OperatingBudgetComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
        OperatingBudgetComponent budgetComponent = new OperatingBudgetComponent();
        budgetComponent.setDescription(rs.getString("description"));
        budgetComponent.setCost(rs.getBigDecimal("cost"));
        return budgetComponent;
    }
}
