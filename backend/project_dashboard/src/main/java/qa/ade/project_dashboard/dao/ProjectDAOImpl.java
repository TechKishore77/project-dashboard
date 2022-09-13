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
import qa.ade.project_dashboard.model.mapper.ProjectMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_DELETE_BY_ID = "DELETE FROM PROJECT WHERE id = ?";
    private static final String SQL_SELECT_PROJECT_BY_ID = "SELECT * FROM PROJECT WHERE id = ?";
    private static final String SQL_SELECT_ALL_PROJECTS = "SELECT * FROM PROJECT";
    private static final String SQL_INSERT_PROJECT = "INSERT INTO PROJECT" +
            "(contract_no," +
            "contract_title," +
            "client, " +
            "consultant," +
            "type_of_contract," +
            "form_of_contract, " +
            "contract_value," +
            "start_date, " +
            "completion_date, " +
            "maintenance_period, " +
            "status, " +
            "added_by)" +
            "VALUES" +
            "(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_PROJECT = "UPDATE PROJECT" +
            "SET contract_no = ?," +
            " contract_title = ?," +
            " client = ?," +
            " consultant = ?," +
            " type_of_contract = ?," +
            " form_of_contract = ?," +
            "contract_value = ?," +
            " start_date=?," +
            " completion_date=?," +
            " maintenance_period=?," +
            " status=? " +
            " where id = ?";

    @Autowired
    public ProjectDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Project getProjectById(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_BY_ID, new ProjectMapper(), id);
    }

    public List<Project> getAllProjects() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PROJECTS, new ProjectMapper());
    }

    @Transactional
    public long createProject(Project project) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PROJECT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getContractNo());
            ps.setString(2, project.getContractTitle());
            ps.setString(3, project.getClient());
            ps.setString(4, project.getConsultant());
            ps.setString(5, project.getTypeOfContract());
            ps.setString(6, project.getFormOfContract());
            ps.setBigDecimal(7, project.getContractValue());
            ps.setDate(8, project.getStartDate());
            ps.setDate(9, project.getCompletionDate());
            ps.setInt(10, project.getMaintenancePeriod());
            ps.setString(11, project.getStatus());
            ps.setString(12, project.getAddedBy());
            return ps;
        }, keyHolder);
        return (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }

    @Transactional
    public boolean editProject(Project project) {
        return jdbcTemplate.update(SQL_UPDATE_PROJECT,
                new Object[]{
                        project.getContractNo(),
                        project.getContractTitle(),
                        project.getClient(),
                        project.getConsultant(),
                        project.getTypeOfContract(),
                        project.getFormOfContract(),
                        project.getContractValue(),
                        project.getStartDate(),
                        project.getCompletionDate(),
                        project.getMaintenancePeriod(),
                        project.getStatus(),
                        project.getId()
                },
                new int[]{
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.DECIMAL,
                        Types.DATE,
                        Types.DATE,
                        Types.INTEGER,
                        Types.VARCHAR,
                        Types.INTEGER
                }
        ) > 0;
    }

    @Transactional
    public boolean deleteProject(Project project) {
        return jdbcTemplate.update(SQL_DELETE_BY_ID, project.getId()) > 0;
    }
}
