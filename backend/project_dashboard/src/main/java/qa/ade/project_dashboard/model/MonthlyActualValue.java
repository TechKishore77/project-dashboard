package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public class MonthlyActualValue {
    private String month;
    private BigDecimal actualValue;
    private BigDecimal actualPercentage;

    public MonthlyActualValue() {
    }

    public MonthlyActualValue(String month, BigDecimal actualValue, BigDecimal actualPercentage) {
        this.month = month;
        this.actualValue = actualValue;
        this.actualPercentage = actualPercentage;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getActualValue() {
        return actualValue;
    }

    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }

    public BigDecimal getActualPercentage() {
        return actualPercentage;
    }

    public void setActualPercentage(BigDecimal actualPercentage) {
        this.actualPercentage = actualPercentage;
    }

    public String toString() {
        return "MonthlyActualValue{" +
                "month='" + month + '\'' +
                ", actualValue=" + actualValue +
                ", actualPercentage=" + actualPercentage +
                '}';
    }
}
