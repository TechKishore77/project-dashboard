package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.OperatingBudget;
import qa.ade.project_dashboard.model.OperatingBudgetComponent;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.OperatingBudgetComponentMapper;
import qa.ade.project_dashboard.model.mapper.OperatingBudgetMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("operatingBudgetDAO")
public class OperatingBudgetDAOImpl implements OperatingBudgetDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_OPERATING_BUDGET = "SELECT * FROM OPERATING_BUDGET WHERE project = ?";
    private static final String SQL_INSERT_OPERATING_BUDGET = "INSERT INTO OPERATING_BUDGET(total_budget, project) VALUES (?,?)";
    private static final String SQL_DELETE_OPERATING_BUDGET = "DELETE FROM OPERATING_BUDGET WHERE project = ?";
    private static final String SQL_GET_BUDGET_COMPONENTS = "SELECT * FROM OPERATING_BUDGET_COMPONENT WHERE budget = ?";
    private static final String SQL_INSERT_BUDGET_COMPONENTS = "INSERT INTO OPERATING_BUDGET_COMPONENT(description, cost, budget) VALUES(?,?,?)";

    public OperatingBudget getOperatingBudget(Project project) {
        return getOperatingBudget(project.getId());
    }

    public OperatingBudget getOperatingBudget(long projectId) {
        OperatingBudget budget = jdbcTemplate.queryForObject(SQL_GET_OPERATING_BUDGET, new OperatingBudgetMapper(), projectId);
        if (budget != null) {
            List<OperatingBudgetComponent> components = jdbcTemplate.query(SQL_GET_BUDGET_COMPONENTS, new OperatingBudgetComponentMapper(), budget.getId());
            budget.setComponents(components);
        }
        return budget;
    }

    @Transactional
    public boolean updateOperatingBudget(Project project, OperatingBudget budget) {
        return updateOperatingBudget(project.getId(), budget);
    }

    @Transactional
    public boolean updateOperatingBudget(long projectId, OperatingBudget budget) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_DELETE_OPERATING_BUDGET, new Object[]{projectId}, new int[]{Types.INTEGER});
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_OPERATING_BUDGET, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, budget.getTotalBudget());
            ps.setLong(2, projectId);
            return ps;
        }, keyHolder);
        long newId = (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
        int[] argTypes = {Types.VARCHAR, Types.DECIMAL, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (OperatingBudgetComponent component : budget.getComponents()) {
            batchArgs.add(new Object[]{component.getDescription(), component.getCost(), newId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_BUDGET_COMPONENTS, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }
}
