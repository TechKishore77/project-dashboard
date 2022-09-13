package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectProperty;
import qa.ade.project_dashboard.model.mapper.ProjectPropertyMapper;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("projectPropertyDAO")
public class ProjectPropertyDAOImpl implements ProjectPropertyDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_BATCH_INSERT_PROJECT_PROP = "INSERT INTO PROJECT_PROPERTY(name, description, value, project) VALUES (?,?,?,?)";
    private static final String SQL_BATCH_DELETE_PROJECT_PROP = "DELETE FROM PROJECT_PROPERTY WHERE id = ? AND project = ?";
    private static final String SQL_GET_PROJECT_PROPERTIES = "SELECT * FROM PROJECT_PROPERTY WHERE project = ?";

    @Autowired
    public ProjectPropertyDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public boolean addProjectProperties(Project project, List<ProjectProperty> properties) {
        return addProjectProperties(project.getId(), properties);
    }

    @Transactional
    public boolean addProjectProperties(long projectId, List<ProjectProperty> properties) {
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (ProjectProperty property : properties) {
            batchArgs.add(new Object[]{property.getName(), property.getDescription(), property.getValue(), projectId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_BATCH_INSERT_PROJECT_PROP, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }

    @Transactional
    public boolean editProjectProperties(Project project, List<ProjectProperty> properties) {
        return editProjectProperties(project.getId(), properties);
    }

    @Transactional
    public boolean editProjectProperties(long projectId, List<ProjectProperty> properties) {
        boolean ret = deleteProjectProperties(projectId, properties);
        if (ret)
            return addProjectProperties(projectId, properties);
        return false;
    }

    @Transactional
    public boolean deleteProjectProperties(Project project, List<ProjectProperty> properties) {
        return deleteProjectProperties(project.getId(), properties);
    }

    @Transactional
    public boolean deleteProjectProperties(long projectId, List<ProjectProperty> properties) {
        int[] argTypes = {Types.INTEGER, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (ProjectProperty property : properties) {
            batchArgs.add(new Object[]{property.getId(), projectId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_BATCH_DELETE_PROJECT_PROP, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret < 0) return false;
        }
        return true;
    }

    public List<ProjectProperty> getProjectProperties(Project project) {
        return getProjectProperties(project.getId());
    }

    public List<ProjectProperty> getProjectProperties(long projectId) {
        return jdbcTemplate.query(SQL_GET_PROJECT_PROPERTIES, new ProjectPropertyMapper(), projectId);
    }
}
