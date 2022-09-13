package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectProgress;
import qa.ade.project_dashboard.model.mapper.ProjectProgressMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("projectProgressDAO")
public class ProjectProgressDAOImpl implements ProjectProgressDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_PROJECT_PROGRESS = "SELECT * FROM PROJECT_PROGRESS WHERE project = ?";
    private static final String SQL_GET_LATEST_PROGRESS_UPDATE = "SELECT * FROM PROJECT_PROGRESS LIMIT 1" +
            "WHERE data_date = (SELECT MAX(data_date) FROM PROJECT_PROGRESS WHERE project = ?) AND project = ?";

    private static final String SQL_INSERT_PROJECT_PROGRESS = "INSERT INTO PROJECT_PROGRESS(data_date, progress_percentage, forecast_completion_date, project)" +
            " VALUES(?,?,?,?)";

    public List<ProjectProgress> getProjectProgress(Project project) {
        return getProjectProgress(project.getId());
    }

    public List<ProjectProgress> getProjectProgress(long projectId) {
        return jdbcTemplate.query(SQL_GET_PROJECT_PROGRESS, new ProjectProgressMapper(), projectId);
    }

    public ProjectProgress getLatestProgressUpdate(Project project) {
        return getLatestProgressUpdate(project.getId());
    }

    public ProjectProgress getLatestProgressUpdate(long projectId) {
        return jdbcTemplate.queryForObject(SQL_GET_LATEST_PROGRESS_UPDATE, new ProjectProgressMapper(), projectId, projectId);
    }

    public long addProjectProgressUpdate(ProjectProgress projectProgress, Project project) {
        return addProjectProgressUpdate(projectProgress, project.getId());
    }

    @Transactional
    public long addProjectProgressUpdate(ProjectProgress projectProgress, long projectId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PROJECT_PROGRESS, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, projectProgress.getDataDate());
            ps.setBigDecimal(2, projectProgress.getProgressPercentage());
            ps.setDate(3, projectProgress.getForecastCompletionDate());
            ps.setLong(4, projectId);
            return ps;
        }, keyHolder);
        return (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }
}
