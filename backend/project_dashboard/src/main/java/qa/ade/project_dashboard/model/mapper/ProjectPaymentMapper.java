package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.ProjectPayment;
import qa.ade.project_dashboard.model.ProjectProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectPaymentMapper implements RowMapper<ProjectPayment> {

    public ProjectPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectPayment projectPayment = new ProjectPayment();
        projectPayment.setId(rs.getLong("id"));
        projectPayment.setIpcNo(rs.getLong("ipc_no"));
        projectPayment.setValueDate(rs.getDate("value_date"));
        projectPayment.setSubmittedDate(rs.getDate("submitted_date"));
        projectPayment.setGrossAmount(rs.getBigDecimal("gross_amount"));
        projectPayment.setAccumulatedValAmount(rs.getBigDecimal("accumulated_val_amount"));
        projectPayment.setNetPayableAmount(rs.getBigDecimal("net_payable_amount"));
        projectPayment.setCertifiedDate(rs.getDate("certified_date"));
        projectPayment.setCertifiedAmount(rs.getBigDecimal("certified_amount"));
        projectPayment.setPaymentDueDate(rs.getDate("payment_due_date"));
        projectPayment.setReceivableAmount(rs.getBigDecimal("receivable_amount"));
        projectPayment.setReceivedAmount(rs.getBigDecimal("received_amount"));
        projectPayment.setReceivedDate(rs.getDate("received_date"));
        projectPayment.setRemarks(rs.getString("remarks"));
        projectPayment.setProjectId(rs.getLong("project_id"));
        return projectPayment;
    }
}
