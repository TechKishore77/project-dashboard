package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.InvoicingPlan;

import java.sql.ResultSet;
import java.sql.SQLException;


public class InvoicingPlanMapper implements RowMapper<InvoicingPlan> {

    public InvoicingPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
        InvoicingPlan invoicingPlan = new InvoicingPlan();
        invoicingPlan.setId(rs.getLong("id"));
        invoicingPlan.setDescription(rs.getString("description"));
        return invoicingPlan;
    }
}
