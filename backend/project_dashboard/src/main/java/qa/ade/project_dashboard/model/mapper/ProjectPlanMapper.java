package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.ProjectPlan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectPlanMapper implements RowMapper<ProjectPlan> {
    public ProjectPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectPlan projectPlan = new ProjectPlan();
        projectPlan.setDescription(rs.getString("description"));
        projectPlan.setId(rs.getLong("id"));
        return projectPlan;
    }
}
