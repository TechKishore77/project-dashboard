package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements RowMapper<Invoice> {

    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setDataDate(rs.getDate("data_date"));
        invoice.setInvoiceAmount(rs.getBigDecimal("invoice_amount"));
        invoice.setCertifiedAmount(rs.getBigDecimal("certified_amount"));
        return invoice;
    }
}
