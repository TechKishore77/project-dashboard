package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.util.List;

public class SCurveData {
    private List<String> months;
    private List<BigDecimal> plannedValues;
    private List<BigDecimal> actualValues;
    private List<BigDecimal> cumulativePlannedPercentValues;
    private List<BigDecimal> cumulativeActualPercentValues;
    private BigDecimal maxValue;

    public SCurveData() {
    }

    public SCurveData(List<String> months, List<BigDecimal> plannedValues, List<BigDecimal> actualValues, List<BigDecimal> cumulativePlannedPercentValues, List<BigDecimal> cumulativeActualPercentValues, BigDecimal maxValue) {
        this.months = months;
        this.plannedValues = plannedValues;
        this.actualValues = actualValues;
        this.cumulativePlannedPercentValues = cumulativePlannedPercentValues;
        this.cumulativeActualPercentValues = cumulativeActualPercentValues;
        this.maxValue = maxValue;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<BigDecimal> getPlannedValues() {
        return plannedValues;
    }

    public void setPlannedValues(List<BigDecimal> plannedValues) {
        this.plannedValues = plannedValues;
    }

    public List<BigDecimal> getActualValues() {
        return actualValues;
    }

    public void setActualValues(List<BigDecimal> actualValues) {
        this.actualValues = actualValues;
    }

    public List<BigDecimal> getCumulativePlannedPercentValues() {
        return cumulativePlannedPercentValues;
    }

    public void setCumulativePlannedPercentValues(List<BigDecimal> cumulativePlannedPercentValues) {
        this.cumulativePlannedPercentValues = cumulativePlannedPercentValues;
    }

    public List<BigDecimal> getCumulativeActualPercentValues() {
        return cumulativeActualPercentValues;
    }

    public void setCumulativeActualPercentValues(List<BigDecimal> cumulativeActualPercentValues) {
        this.cumulativeActualPercentValues = cumulativeActualPercentValues;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String toString() {
        return "SCurveData{" +
                "months=" + months +
                ", plannedValues=" + plannedValues +
                ", actualValues=" + actualValues +
                ", cumulativePlannedPercentValues=" + cumulativePlannedPercentValues +
                ", cumulativeActualPercentValues=" + cumulativeActualPercentValues +
                ", maxValue=" + maxValue +
                '}';
    }
}
