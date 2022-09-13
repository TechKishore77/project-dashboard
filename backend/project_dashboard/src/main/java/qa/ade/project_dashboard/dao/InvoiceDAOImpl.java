package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.Invoice;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.InvoiceMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("invoiceDAO")
public class InvoiceDAOImpl implements InvoiceDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_INVOICE = "SELECT * FROM INVOICE WHERE id = ?";
    private static final String SQL_GET_ALL_INVOICES = "SELECT * FROM INVOICE WHERE project = ?";
    private static final String SQL_INSERT_INVOICE = "INSERT INTO INVOICE(data_date, invoice_amount, certified_amount, project) " +
            "VALUES (?,?,?,?)";

    public Invoice getInvoiceById(long id) {
        return jdbcTemplate.queryForObject(SQL_GET_INVOICE, new InvoiceMapper(), id);
    }

    @Transactional
    public long addInvoice(Invoice invoice, Project project) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INVOICE, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, invoice.getDataDate());
            ps.setBigDecimal(2, invoice.getInvoiceAmount());
            ps.setBigDecimal(3, invoice.getCertifiedAmount());
            ps.setLong(4, project.getId());
            return ps;
        }, keyHolder);
        return (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }

    public List<Invoice> getAllInvoices(Project project) {
        return getAllInvoices(project.getId());
    }

    public List<Invoice> getAllInvoices(long projectId) {
        return jdbcTemplate.query(SQL_GET_ALL_INVOICES, new InvoiceMapper(), projectId);
    }
}
