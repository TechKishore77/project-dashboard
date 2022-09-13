package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.MonthlyActualValue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyActualValueMapper implements RowMapper<MonthlyActualValue> {
    public MonthlyActualValue mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonthlyActualValue monthlyActualValue = new MonthlyActualValue();
        monthlyActualValue.setMonth(rs.getString("month"));
        monthlyActualValue.setActualPercentage(rs.getBigDecimal("actual_percentage"));
        monthlyActualValue.setActualValue(rs.getBigDecimal("actual_value"));
        return monthlyActualValue;
    }
}
