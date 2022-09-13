package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.ProjectProgress;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectProgressMapper implements RowMapper<ProjectProgress> {
    public ProjectProgress mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectProgress projectProgress = new ProjectProgress();
        projectProgress.setDataDate(rs.getDate("data_date"));
        projectProgress.setForecastCompletionDate(rs.getDate("forecast_completion_date"));
        projectProgress.setProgressPercentage(rs.getBigDecimal("progress_percentage"));
        return projectProgress;
    }
}
