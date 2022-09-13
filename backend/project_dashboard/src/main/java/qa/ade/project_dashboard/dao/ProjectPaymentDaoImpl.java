package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import qa.ade.project_dashboard.model.ProjectPayment;
import qa.ade.project_dashboard.model.mapper.ProjectPaymentMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("projectPaymentDao")
public class ProjectPaymentDaoImpl implements ProjectPaymentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_PROJECT_PAYMENT = "INSERT INTO PROJECT_PAYMENT" +
            "(ipc_no," +
            "value_date," +
            "submitted_date, " +
            "gross_amount," +
            "accumulated_val_amount," +
            "net_payable_amount, " +
            "certified_date," +
            "certified_amount, " +
            "payment_due_date, " +
            "receivable_amount, " +
            "received_amount, " +
            "received_date, " +
            "remarks, " +
            "project_id)" +
            "VALUES" +
            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE_PROJECT_PAYMENT = "UPDATE PROJECT_PAYMENT SET" +
            " ipc_no = ?, value_date = ?, submitted_date = ?, gross_amount = ?, accumulated_val_amount = ?," +
            " net_payable_amount = ?, certified_date = ?, certified_amount=?, payment_due_date=?, receivable_amount=?," +
            " received_amount=?, received_date=?, remarks=? " +
            " where id = ?";

    private static final String SQL_SELECT_PROJECT_PAYMENT_BY_ID = "SELECT * FROM PROJECT_PAYMENT WHERE id = ?";

    private static final String SQL_SELECT_PROJECT_PAYMENT_BY_PROJECT_ID = "SELECT * FROM PROJECT_PAYMENT WHERE project_id = ?";

    @Override
    public long createProjectPayment(ProjectPayment projectPayment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PROJECT_PAYMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, projectPayment.getIpcNo());
            ps.setDate(2, projectPayment.getValueDate());
            ps.setDate(3, projectPayment.getSubmittedDate());
            ps.setBigDecimal(4, projectPayment.getGrossAmount());
            ps.setBigDecimal(5, projectPayment.getAccumulatedValAmount());
            ps.setBigDecimal(6, projectPayment.getNetPayableAmount());
            ps.setDate(7, projectPayment.getCertifiedDate());
            ps.setBigDecimal(8, projectPayment.getCertifiedAmount());
            ps.setDate(9, projectPayment.getPaymentDueDate());
            ps.setBigDecimal(10, projectPayment.getReceivableAmount());
            ps.setBigDecimal(11, projectPayment.getReceivedAmount());
            ps.setDate(12, projectPayment.getReceivedDate());
            ps.setString(13, projectPayment.getRemarks());
            ps.setLong(14, projectPayment.getProjectId());
            return ps;
        }, keyHolder);
        return (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }

    public boolean createProjectPayments(List<ProjectPayment> projectPayments) {
        int[] argTypes = {Types.INTEGER, Types.DATE, Types.DATE, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.DATE, Types.DECIMAL,
                Types.DATE, Types.DECIMAL, Types.DECIMAL, Types.DATE, Types.VARCHAR, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        for (ProjectPayment projectPayment : projectPayments) {
            batchArgs.add(new Object[]{projectPayment.getIpcNo(), projectPayment.getValueDate(), projectPayment.getSubmittedDate(),
                    projectPayment.getGrossAmount(), projectPayment.getAccumulatedValAmount(), projectPayment.getNetPayableAmount(),
                    projectPayment.getCertifiedDate(), projectPayment.getCertifiedAmount(),
                    projectPayment.getPaymentDueDate(), projectPayment.getReceivableAmount(),
                    projectPayment.getReceivedAmount(), projectPayment.getReceivedDate(),
                    projectPayment.getRemarks(), projectPayment.getProjectId()});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_PROJECT_PAYMENT, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }

    public boolean editProjectPayment(ProjectPayment projectPayment) {
        int[] argTypes = {Types.INTEGER, Types.DATE, Types.DATE, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.DATE, Types.DECIMAL,
                Types.DATE, Types.DECIMAL, Types.DECIMAL, Types.DATE, Types.VARCHAR, Types.INTEGER};
        return jdbcTemplate.update(SQL_UPDATE_PROJECT_PAYMENT,
                new Object[]{
                        projectPayment.getIpcNo(), projectPayment.getValueDate(), projectPayment.getSubmittedDate(),
                        projectPayment.getGrossAmount(), projectPayment.getAccumulatedValAmount(), projectPayment.getNetPayableAmount(),
                        projectPayment.getCertifiedDate(), projectPayment.getCertifiedAmount(),
                        projectPayment.getPaymentDueDate(), projectPayment.getReceivableAmount(),
                        projectPayment.getReceivedAmount(), projectPayment.getReceivedDate(),
                        projectPayment.getRemarks(), projectPayment.getId()
                }, argTypes) > 0;
    }

    public ProjectPayment getProjectPaymentById(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_PAYMENT_BY_ID, new ProjectPaymentMapper(), id);
    }

    public List<ProjectPayment> getProjectPaymentByProjectId(long projectId) {
        return jdbcTemplate.query(SQL_SELECT_PROJECT_PAYMENT_BY_PROJECT_ID, new ProjectPaymentMapper(), projectId);
    }
}
