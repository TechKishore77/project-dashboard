package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.MonthlyPlannedValue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyPlannedValueMapper implements RowMapper<MonthlyPlannedValue> {
    public MonthlyPlannedValue mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonthlyPlannedValue monthlyPlannedValue = new MonthlyPlannedValue();
        monthlyPlannedValue.setMonth(rs.getString("month"));
        monthlyPlannedValue.setPlannedPercentage(rs.getBigDecimal("planned_percentage"));
        monthlyPlannedValue.setPlannedValue(rs.getBigDecimal("planned_value"));
        return monthlyPlannedValue;
    }
}
