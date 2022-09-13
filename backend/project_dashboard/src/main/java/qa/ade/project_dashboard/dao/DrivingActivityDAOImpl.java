package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.DrivingActivity;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.DrivingActivityMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("drivingActivityDAO")
public class DrivingActivityDAOImpl implements DrivingActivityDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_DRIVING_ACTIVITIES = "SELECT * FROM DRIVING_ACTIVITY WHERE project = ?";
    private static final String SQL_GET_DRIVING_ACTIVITY_BY_ID = "SELECT * FROM DRIVING_ACTIVITY WHERE id = ?";
    private static final String SQL_INSERT_DRIVING_ACTIVITY = "INSERT INTO DRIVING_ACTIVITY(data_date, name, status, issues, action_party, project) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_DRIVING_ACTIVITY = "UPDATE DRIVING_ACTIVITY SET data_date = ?, name = ?, status = ?, " +
            "issues = ?, action_party = ?, project = ? WHERE id = ?";
    private static final String SQL_DELETE_DRIVING_ACTIVITY = "DELETE FROM DRIVING_ACTIVITY WHERE id = ?";

    public List<DrivingActivity> getAllDrivingActivities(Project project) {
        return getAllDrivingActivities(project.getId());
    }

    public List<DrivingActivity> getAllDrivingActivities(long projectId) {
        return jdbcTemplate.query(SQL_GET_ALL_DRIVING_ACTIVITIES, new DrivingActivityMapper(), projectId);
    }

    public DrivingActivity getDrivingActivityById(long id) {
        return jdbcTemplate.queryForObject(SQL_GET_DRIVING_ACTIVITY_BY_ID, new DrivingActivityMapper(), id);
    }

    @Transactional
    public long createDrivingActivity(Project project, DrivingActivity drivingActivity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_DRIVING_ACTIVITY, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, drivingActivity.getDataDate());
            ps.setString(2, drivingActivity.getName());
            ps.setString(3, drivingActivity.getStatus());
            ps.setString(4, drivingActivity.getIssues());
            ps.setString(5, drivingActivity.getActionParty());
            ps.setLong(6, project.getId());
            return ps;
        }, keyHolder);
        Object key = keyHolder.getKey();
        return (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }

    @Transactional
    public boolean createDrivingActivities(Project project, List<DrivingActivity> drivingActivities) {
        int[] argTypes = {Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (DrivingActivity drivingActivity : drivingActivities) {
            batchArgs.add(new Object[]{drivingActivity.getDataDate(), drivingActivity.getName(), drivingActivity.getStatus(), drivingActivity.getIssues(), drivingActivity.getActionParty(), project.getId()});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_DRIVING_ACTIVITY, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }

    @Transactional
    public boolean editDrivingActivity(DrivingActivity drivingActivity) {
        return jdbcTemplate.update(SQL_UPDATE_DRIVING_ACTIVITY,
                new Object[]{
                        drivingActivity.getDataDate(),
                        drivingActivity.getName(),
                        drivingActivity.getStatus(),
                        drivingActivity.getIssues(),
                        drivingActivity.getActionParty(),
                        drivingActivity.getProject().getId(),
                        drivingActivity.getId()
                },
                new int[]{
                        Types.DATE,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.INTEGER,
                        Types.INTEGER
                }
        ) > 0;
    }

    @Transactional
    public boolean deleteDrivingActivity(DrivingActivity drivingActivity) {
        return jdbcTemplate.update(SQL_DELETE_DRIVING_ACTIVITY, drivingActivity.getId()) > 0;
    }
}
