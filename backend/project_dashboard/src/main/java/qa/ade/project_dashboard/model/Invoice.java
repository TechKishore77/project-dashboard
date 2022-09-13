package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Invoice {
    private Date dataDate;
    private BigDecimal invoiceAmount;
    private BigDecimal certifiedAmount;

    public Invoice() {
        dataDate = Date.valueOf(LocalDate.now());
        invoiceAmount = new BigDecimal("0");
        certifiedAmount = new BigDecimal("0");
    }

    public Invoice(Date dataDate, BigDecimal invoiceAmount, BigDecimal certifiedAmount) {
        this.dataDate = dataDate;
        this.invoiceAmount = invoiceAmount;
        this.certifiedAmount = certifiedAmount;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public BigDecimal getCertifiedAmount() {
        return certifiedAmount;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public void setCertifiedAmount(BigDecimal certifiedAmount) {
        this.certifiedAmount = certifiedAmount;
    }

    public String toString() {
        return "Invoice{" +
                "dataDate=" + dataDate +
                ", invoiceAmount=" + invoiceAmount +
                '}';
    }
}
