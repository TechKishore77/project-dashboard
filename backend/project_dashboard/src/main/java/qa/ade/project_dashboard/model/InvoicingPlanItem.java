package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class InvoicingPlanItem {
    private Date day;
    private BigDecimal amount;
    private BigDecimal cumulativeAmount;

    public InvoicingPlanItem() {
        this.day = Date.valueOf(LocalDate.now());
        amount = new BigDecimal("0");
        cumulativeAmount = new BigDecimal("0");
    }

    public InvoicingPlanItem(Date day, BigDecimal amount, BigDecimal cumulativeAmount) {
        this.day = day;
        this.amount = amount;
        this.cumulativeAmount = cumulativeAmount;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCumulativeAmount() {
        return cumulativeAmount;
    }

    public void setCumulativeAmount(BigDecimal cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
    }

    public String toString() {
        return "InvoicePlanItem{" +
                "day=" + day +
                ", amount=" + amount +
                ", cumulativeAmount=" + cumulativeAmount +
                '}';
    }
}
