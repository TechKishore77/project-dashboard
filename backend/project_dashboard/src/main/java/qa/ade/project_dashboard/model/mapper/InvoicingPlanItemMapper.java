package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.InvoicingPlanItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoicingPlanItemMapper implements RowMapper<InvoicingPlanItem> {
    public InvoicingPlanItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        InvoicingPlanItem invoicingPlanItem = new InvoicingPlanItem();
        invoicingPlanItem.setAmount(rs.getBigDecimal("amount"));
        invoicingPlanItem.setCumulativeAmount(rs.getBigDecimal("cum_amount"));
        invoicingPlanItem.setDay(rs.getDate("day"));
        return invoicingPlanItem;
    }
}
