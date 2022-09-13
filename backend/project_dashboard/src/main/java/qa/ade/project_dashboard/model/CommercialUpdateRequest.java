package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class CommercialUpdateRequest {
    private Date invoiceDate;
    private BigDecimal latestInvoiceAmount;
    private BigDecimal certifiedAmount;
    private BigDecimal profit;
    private AnticipatedBudget anticipatedBudget;

    public CommercialUpdateRequest() {
        invoiceDate = Date.valueOf(LocalDate.now());
        latestInvoiceAmount = new BigDecimal("0");
        certifiedAmount = new BigDecimal("0");
        profit = new BigDecimal("0");
        anticipatedBudget = new AnticipatedBudget();
    }

    public CommercialUpdateRequest(Date invoiceDate, BigDecimal latestInvoiceAmount, BigDecimal certifiedAmount, BigDecimal profit, AnticipatedBudget anticipatedBudget) {
        this.invoiceDate = invoiceDate;
        this.latestInvoiceAmount = latestInvoiceAmount;
        this.certifiedAmount = certifiedAmount;
        this.profit = profit;
        this.anticipatedBudget = anticipatedBudget;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public BigDecimal getLatestInvoiceAmount() {
        return latestInvoiceAmount;
    }

    public BigDecimal getCertifiedAmount() {
        return certifiedAmount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public AnticipatedBudget getAnticipatedBudget() {
        return anticipatedBudget;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setLatestInvoiceAmount(BigDecimal latestInvoiceAmount) {
        this.latestInvoiceAmount = latestInvoiceAmount;
    }

    public void setCertifiedAmount(BigDecimal certifiedAmount) {
        this.certifiedAmount = certifiedAmount;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public void setAnticipatedBudget(AnticipatedBudget anticipatedBudget) {
        this.anticipatedBudget = anticipatedBudget;
    }

    public String toString() {
        return "CommercialUpdateRequest{" +
                "invoiceDate=" + invoiceDate +
                ", latestInvoiceAmount=" + latestInvoiceAmount +
                ", certifiedAmount=" + certifiedAmount +
                ", profit=" + profit +
                ", anticipatedBudget=" + anticipatedBudget +
                '}';
    }
}
