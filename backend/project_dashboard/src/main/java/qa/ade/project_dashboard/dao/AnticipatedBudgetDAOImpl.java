package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.AnticipatedBudget;
import qa.ade.project_dashboard.model.AnticipatedBudgetComponent;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.AnticipatedBudgetComponentMapper;
import qa.ade.project_dashboard.model.mapper.AnticipatedBudgetMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("anticipatedBudgetDAO")
public class AnticipatedBudgetDAOImpl implements AnticipatedBudgetDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ANTICIPATED_BUDGET = "SELECT * FROM ANTICIPATED_BUDGET WHERE project = ?";
    private static final String SQL_GET_LATEST_ANTICIPATED_BUDGET = "SELECT * FROM ANTICIPATED_BUDGET WHERE project = ? AND data_date = (SELECT max(data_date) FROM ANTICIPATED_BUDGET WHERE project = ?) ORDER BY updated_on DESC LIMIT 1";
    private static final String SQL_GET_ANTICIPATED_BUDGET_COMPONENT = "SELECT * FROM ANTICIPATED_BUDGET_COMPONENT WHERE budget = ?";
    private static final String SQL_INSERT_ANTICIPATED_BUDGET = "INSERT INTO ANTICIPATED_BUDGET(total_budget, data_date, project) VALUES (?,?,?)";
    private static final String SQL_INSERT_ANTICIPATED_BUDGET_COMPONENTS = "INSERT INTO ANTICIPATED_BUDGET_COMPONENT(description, cost, budget) VALUES(?,?,?)";

    public List<AnticipatedBudget> getAnticipatedBudgets(Project project) {
        return getAnticipatedBudgets(project.getId());
    }

    public List<AnticipatedBudget> getAnticipatedBudgets(long projectId) {
        List<AnticipatedBudget> budgets = jdbcTemplate.query(SQL_GET_ANTICIPATED_BUDGET, new AnticipatedBudgetMapper(), projectId);
        for (AnticipatedBudget budget : budgets) {
            List<AnticipatedBudgetComponent> components = jdbcTemplate.query(SQL_GET_ANTICIPATED_BUDGET_COMPONENT, new AnticipatedBudgetComponentMapper(), budget.getId());
            budget.setComponents(components);
        }
        return budgets;
    }

    public AnticipatedBudget getLatestAnticipatedBudget(long projectId) {
        AnticipatedBudget budget;
        try {
            budget = jdbcTemplate.queryForObject(SQL_GET_LATEST_ANTICIPATED_BUDGET, new AnticipatedBudgetMapper(), projectId, projectId);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
        if (budget != null) {
            List<AnticipatedBudgetComponent> components = jdbcTemplate.query(SQL_GET_ANTICIPATED_BUDGET_COMPONENT, new AnticipatedBudgetComponentMapper(), budget.getId());
            budget.setComponents(components);
        }
        return budget;
    }

    public boolean addAnticipatedBudget(Project project, AnticipatedBudget budget) {
        return addAnticipatedBudget(project.getId(), budget);
    }

    @Transactional
    public boolean addAnticipatedBudget(long projectId, AnticipatedBudget budget) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ANTICIPATED_BUDGET, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, budget.getTotalBudget());
            ps.setDate(2, budget.getDataDate());
            ps.setLong(3, projectId);
            return ps;
        }, keyHolder);
        long newId = (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
        int[] argTypes = {Types.VARCHAR, Types.DECIMAL, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (AnticipatedBudgetComponent component : budget.getComponents()) {
            batchArgs.add(new Object[]{component.getDescription(), component.getCost(), newId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_ANTICIPATED_BUDGET_COMPONENTS, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }
}
