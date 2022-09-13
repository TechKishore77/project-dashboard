package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.InvoicingPlan;
import qa.ade.project_dashboard.model.InvoicingPlanItem;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.InvoicingPlanItemMapper;
import qa.ade.project_dashboard.model.mapper.InvoicingPlanMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("invoicingPlanDAO")
public class InvoicingPlanDAOImpl implements InvoicingPlanDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_INV_PLAN = "INSERT INTO INVOICING_PLAN(description, project) VALUES (?,?)";
    private static final String SQL_GET_INV_PLAN = "SELECT * FROM INVOICING_PLAN WHERE project = ?";
    private static final String SQL_GET_INV_PLAN_ITEMS = "SELECT * FROM INVOICING_PLAN_ITEM WHERE invoicing_plan = ?";
    private static final String SQL_DELETE_INV_PLAN = "DELETE FROM INVOICING_PLAN WHERE project = ?";
    private static final String SQL_INSERT_INV_PLAN_ITEM = "INSERT INTO INVOICING_PLAN_ITEM(day, amount, cum_amount, invoicing_plan) VALUES(?,?,?,?)";

    public InvoicingPlan getInvoicingPlan(long projectId) {
        InvoicingPlan invoicingPlan = jdbcTemplate.queryForObject(SQL_GET_INV_PLAN, new InvoicingPlanMapper(), projectId);
        if (invoicingPlan != null) {
            List<InvoicingPlanItem> invoices = jdbcTemplate.query(SQL_GET_INV_PLAN_ITEMS, new InvoicingPlanItemMapper(), invoicingPlan.getId());
            invoicingPlan.setInvoices(invoices);
        }
        return invoicingPlan;
    }

    @Transactional
    public boolean updateInvoicingPlan(Project project, InvoicingPlan invoicingPlan) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_DELETE_INV_PLAN, new Object[]{project.getId()}, new int[]{Types.INTEGER});
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INV_PLAN, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, invoicingPlan.getDescription());
            ps.setLong(2, project.getId());
            return ps;
        }, keyHolder);
        long newId = (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
        int[] argTypes = {Types.DATE, Types.DECIMAL, Types.DECIMAL, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (InvoicingPlanItem invoice : invoicingPlan.getInvoices()) {
            batchArgs.add(new Object[]{invoice.getDay(), invoice.getAmount(), invoice.getCumulativeAmount(), newId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_INV_PLAN_ITEM, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }
}
