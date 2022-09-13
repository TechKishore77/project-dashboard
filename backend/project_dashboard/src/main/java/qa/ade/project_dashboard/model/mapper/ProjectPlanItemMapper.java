package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.ProjectPlanItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectPlanItemMapper implements RowMapper<ProjectPlanItem> {
    public ProjectPlanItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectPlanItem projectPlanItem = new ProjectPlanItem();
        projectPlanItem.setCumulativePV(rs.getBigDecimal("cum_pv"));
        projectPlanItem.setCumulativePVPercent(rs.getBigDecimal("cum_pv_percent"));
        projectPlanItem.setPlannedValue(rs.getBigDecimal("planned_value"));
        return projectPlanItem;
    }
}
