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
import qa.ade.project_dashboard.model.ProjectPlan;
import qa.ade.project_dashboard.model.ProjectPlanItem;
import qa.ade.project_dashboard.model.mapper.ProjectPlanItemMapper;
import qa.ade.project_dashboard.model.mapper.ProjectPlanMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("projectPlanDAO")
public class ProjectPlanDAOImpl implements ProjectPlanDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_PROJECT_PLAN = "INSERT INTO PROJECT_PLAN(description, project) VALUES (?,?)";
    private static final String SQL_INSERT_PROJECT_PLAN_ITEM = "INSERT INTO PROJECT_PLAN_ITEM(day, planned_value, cum_pv, cum_pv_percent, plan) VALUES (?,?,?,?,?)";
    private static final String SQL_GET_PROJECT_PLAN = "SELECT * FROM PROJECT_PLAN WHERE project = ?";
    private static final String SQL_GET_PROJECT_PLAN_ITEM = "SELECT * FROM PROJECT_PLAN_ITEM WHERE plan = ?";
    private static final String SQL_DELETE_PROJECT_PLAN = "DELETE FROM PROJECT_PLAN WHERE project = ?";

    public ProjectPlan getProjectPlan(long projectId) {
        ProjectPlan projectPlan = jdbcTemplate.queryForObject(SQL_GET_PROJECT_PLAN, new ProjectPlanMapper(), projectId);
        if (projectPlan != null) {
            List<ProjectPlanItem> invoices = jdbcTemplate.query(SQL_GET_PROJECT_PLAN_ITEM, new ProjectPlanItemMapper(), projectPlan.getId());
            projectPlan.setPlanItems(invoices);
        }
        return projectPlan;
    }

    @Transactional
    public boolean updateProjectPlan(Project project, ProjectPlan projectPlan) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_DELETE_PROJECT_PLAN, new Object[]{project.getId()}, new int[]{Types.INTEGER});
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PROJECT_PLAN, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projectPlan.getDescription());
            ps.setLong(2, project.getId());
            return ps;
        }, keyHolder);
        long newId = (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
        int[] argTypes = {Types.DATE, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (ProjectPlanItem planItem : projectPlan.getPlanItems()) {
            batchArgs.add(new Object[]{planItem.getDay(), planItem.getPlannedValue(), planItem.getCumulativePV(), planItem.getCumulativePVPercent(), newId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_PROJECT_PLAN_ITEM, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }
}
