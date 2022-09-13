package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;

public class ProjectPlanItem {
    private Date day;
    private BigDecimal plannedValue;
    private BigDecimal cumulativePV;
    private BigDecimal cumulativePVPercent;

    public ProjectPlanItem() {
    }

    public ProjectPlanItem(Date day, BigDecimal plannedValue, BigDecimal cumulativePV, BigDecimal cumulativePVPercent) {
        this.day = day;
        this.plannedValue = plannedValue;
        this.cumulativePV = cumulativePV;
        this.cumulativePVPercent = cumulativePVPercent;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public BigDecimal getPlannedValue() {
        return plannedValue;
    }

    public void setPlannedValue(BigDecimal plannedValue) {
        this.plannedValue = plannedValue;
    }

    public BigDecimal getCumulativePV() {
        return cumulativePV;
    }

    public void setCumulativePV(BigDecimal cumulativePV) {
        this.cumulativePV = cumulativePV;
    }

    public BigDecimal getCumulativePVPercent() {
        return cumulativePVPercent;
    }

    public void setCumulativePVPercent(BigDecimal cumulativePVPercent) {
        this.cumulativePVPercent = cumulativePVPercent;
    }

    public String toString() {
        return "ProjectPlanItem{" +
                "day=" + day +
                ", plannedValue=" + plannedValue +
                ", cumulativePV=" + cumulativePV +
                ", cumulativePVPercent=" + cumulativePVPercent +
                '}';
    }
}
