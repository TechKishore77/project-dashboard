package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Map;

public class PaymentReportDataItem {
    Project project;
    BigDecimal earnedValue;
    BigDecimal cumulativePlannedAmount;
    Date valueDate;
    Date submittedDate;
    BigDecimal latestGrossAmount;
    BigDecimal latestReceivedAmount;
    Date lastReceivedDate;
    BigDecimal accumulatedGrossAmount;
    BigDecimal cumulativeReceivedAmount;

    public PaymentReportDataItem() {
    }

    public PaymentReportDataItem(Project project, BigDecimal earnedValue, BigDecimal cumulativePlannedAmount, Date valueDate, Date submittedDate, BigDecimal latestGrossAmount, BigDecimal latestReceivedAmount, Date lastReceivedDate, BigDecimal accumulatedGrossAmount, BigDecimal cumulativeReceivedAmount) {
        this.project = project;
        this.earnedValue = earnedValue;
        this.cumulativePlannedAmount = cumulativePlannedAmount;
        this.valueDate = valueDate;
        this.submittedDate = submittedDate;
        this.latestGrossAmount = latestGrossAmount;
        this.latestReceivedAmount = latestReceivedAmount;
        this.lastReceivedDate = lastReceivedDate;
        this.accumulatedGrossAmount = accumulatedGrossAmount;
        this.cumulativeReceivedAmount = cumulativeReceivedAmount;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getEarnedValue() {
        return earnedValue;
    }

    public void setEarnedValue(BigDecimal earnedValue) {
        this.earnedValue = earnedValue;
    }

    public BigDecimal getCumulativePlannedAmount() {
        return cumulativePlannedAmount;
    }

    public void setCumulativePlannedAmount(BigDecimal cumulativePlannedAmount) {
        this.cumulativePlannedAmount = cumulativePlannedAmount;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public BigDecimal getLatestGrossAmount() {
        return latestGrossAmount;
    }

    public void setLatestGrossAmount(BigDecimal latestGrossAmount) {
        this.latestGrossAmount = latestGrossAmount;
    }

    public BigDecimal getLatestReceivedAmount() {
        return latestReceivedAmount;
    }

    public void setLatestReceivedAmount(BigDecimal latestReceivedAmount) {
        this.latestReceivedAmount = latestReceivedAmount;
    }

    public Date getLastReceivedDate() {
        return lastReceivedDate;
    }

    public void setLastReceivedDate(Date lastReceivedDate) {
        this.lastReceivedDate = lastReceivedDate;
    }

    public BigDecimal getAccumulatedGrossAmount() {
        return accumulatedGrossAmount;
    }

    public void setAccumulatedGrossAmount(BigDecimal accumulatedGrossAmount) {
        this.accumulatedGrossAmount = accumulatedGrossAmount;
    }

    public BigDecimal getCumulativeReceivedAmount() {
        return cumulativeReceivedAmount;
    }

    public void setCumulativeReceivedAmount(BigDecimal cumulativeReceivedAmount) {
        this.cumulativeReceivedAmount = cumulativeReceivedAmount;
    }

    public String toString() {
        return "PaymentReportDataItem{" +
                "project=" + project +
                ", earnedValue=" + earnedValue +
                ", cumulativePlannedAmount=" + cumulativePlannedAmount +
                ", valueDate=" + valueDate +
                ", submittedDate=" + submittedDate +
                ", latestGrossAmount=" + latestGrossAmount +
                ", latestReceivedAmount=" + latestReceivedAmount +
                ", lastReceivedDate=" + lastReceivedDate +
                ", accumulatedGrossAmount=" + accumulatedGrossAmount +
                ", cumulativeReceivedAmount=" + cumulativeReceivedAmount +
                '}';
    }

    public static PaymentReportDataItem fromMap(Map<String, Object> map) {
        PaymentReportDataItem paymentReportDataItem = new PaymentReportDataItem();
        paymentReportDataItem.setEarnedValue(((BigDecimal) map.getOrDefault("earned_value", 0)).setScale(2, RoundingMode.HALF_UP));
        paymentReportDataItem.setCumulativePlannedAmount(((BigDecimal) map.getOrDefault("cum_planned_amt", 0)).setScale(2, RoundingMode.HALF_UP));
        paymentReportDataItem.setValueDate((Date) map.getOrDefault("value_date", null));
        paymentReportDataItem.setSubmittedDate((Date) map.getOrDefault("submitted_date", null));
        paymentReportDataItem.setLatestGrossAmount(((BigDecimal) map.getOrDefault("latest_gross_amt", 0)).setScale(2, RoundingMode.HALF_UP));
        paymentReportDataItem.setLatestReceivedAmount(((BigDecimal) map.getOrDefault("latest_received_amt", 0)).setScale(2, RoundingMode.HALF_UP));
        paymentReportDataItem.setLastReceivedDate((Date) map.getOrDefault("received_date", null));
        paymentReportDataItem.setAccumulatedGrossAmount(((BigDecimal) map.getOrDefault("accumulated_gross_amt", 0)).setScale(2, RoundingMode.HALF_UP));
        paymentReportDataItem.setCumulativeReceivedAmount(((BigDecimal) map.getOrDefault("cumulative_received_amt", 0)).setScale(2, RoundingMode.HALF_UP));
        Project project = new Project();
        project.setId((int) map.getOrDefault("proj", 0));
        project.setContractTitle((String) map.getOrDefault("contract_title", ""));
        paymentReportDataItem.setProject(project);
        return paymentReportDataItem;
    }
}
