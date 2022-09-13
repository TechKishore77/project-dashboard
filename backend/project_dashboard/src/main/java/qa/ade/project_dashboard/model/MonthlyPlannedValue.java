package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public class MonthlyPlannedValue {
    private String month;
    private BigDecimal plannedValue;
    private BigDecimal plannedPercentage;

    public MonthlyPlannedValue() {
    }

    public MonthlyPlannedValue(String month, BigDecimal plannedValue, BigDecimal plannedPercentage) {
        this.month = month;
        this.plannedValue = plannedValue;
        this.plannedPercentage = plannedPercentage;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getPlannedValue() {
        return plannedValue;
    }

    public void setPlannedValue(BigDecimal plannedValue) {
        this.plannedValue = plannedValue;
    }

    public BigDecimal getPlannedPercentage() {
        return plannedPercentage;
    }

    public void setPlannedPercentage(BigDecimal plannedPercentage) {
        this.plannedPercentage = plannedPercentage;
    }

    public String toString() {
        return "MonthlyPlannedValue{" +
                "month='" + month + '\'' +
                ", plannedValue=" + plannedValue +
                ", plannedPercentage=" + plannedPercentage +
                '}';
    }
}
