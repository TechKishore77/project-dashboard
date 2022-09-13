package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;

public class InvoiceReportItem {
    private Project project;
    private BigDecimal grossInvoiceAmount;
    private Date lastPaymentReceivedDate;

    public InvoiceReportItem() {
    }

    public InvoiceReportItem(Project project, BigDecimal grossInvoiceAmount, Date lastPaymentReceivedDate) {
        this.project = project;
        this.grossInvoiceAmount = grossInvoiceAmount;
        this.lastPaymentReceivedDate = lastPaymentReceivedDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getGrossInvoiceAmount() {
        return grossInvoiceAmount;
    }

    public void setGrossInvoiceAmount(BigDecimal grossInvoiceAmount) {
        this.grossInvoiceAmount = grossInvoiceAmount;
    }

    public Date getLastPaymentReceivedDate() {
        return lastPaymentReceivedDate;
    }

    public void setLastPaymentReceivedDate(Date lastPaymentReceivedDate) {
        this.lastPaymentReceivedDate = lastPaymentReceivedDate;
    }

    public String toString() {
        return "InvoiceReportItem{" +
                "project=" + project +
                ", grossInvoiceAmount=" + grossInvoiceAmount +
                ", lastPaymentReceivedDate=" + lastPaymentReceivedDate +
                '}';
    }
}
