package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.DrivingActivity;
import qa.ade.project_dashboard.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrivingActivityMapper implements RowMapper<DrivingActivity> {
    public DrivingActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
        DrivingActivity drivingActivity = new DrivingActivity();
        drivingActivity.setActionParty(rs.getString("action_party"));
        drivingActivity.setDataDate(rs.getDate("data_date"));
        drivingActivity.setIssues(rs.getString("issues"));
        drivingActivity.setName(rs.getString("name"));
        drivingActivity.setStatus(rs.getString("status"));
        Project project = new Project();
        project.setId(rs.getLong("project"));
        drivingActivity.setId(rs.getLong("id"));
        drivingActivity.setProject(project);
        return drivingActivity;
    }
}
