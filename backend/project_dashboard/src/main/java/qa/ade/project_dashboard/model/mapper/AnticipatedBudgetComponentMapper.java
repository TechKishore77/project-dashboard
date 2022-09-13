package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.AnticipatedBudgetComponent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnticipatedBudgetComponentMapper implements RowMapper<AnticipatedBudgetComponent> {

    public AnticipatedBudgetComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnticipatedBudgetComponent anticipatedBudgetComponent = new AnticipatedBudgetComponent();
        anticipatedBudgetComponent.setCost(rs.getBigDecimal("cost"));
        anticipatedBudgetComponent.setDescription(rs.getString("description"));
        return anticipatedBudgetComponent;
    }
}
