package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ProfitReportDataItem {
    Project project;
    BigDecimal plannedProfit;
    BigDecimal actualProfit;

    public ProfitReportDataItem() {
    }

    public ProfitReportDataItem(Project project, BigDecimal plannedProfit, BigDecimal actualProfit) {
        this.project = project;
        this.plannedProfit = plannedProfit;
        this.actualProfit = actualProfit;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getPlannedProfit() {
        return plannedProfit;
    }

    public void setPlannedProfit(BigDecimal plannedProfit) {
        this.plannedProfit = plannedProfit;
    }

    public BigDecimal getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(BigDecimal actualProfit) {
        this.actualProfit = actualProfit;
    }

    public String toString() {
        return "ProfitReportDataItem{" +
                "project=" + project +
                ", plannedProfit=" + plannedProfit +
                ", actualProfit=" + actualProfit +
                '}';
    }

    public static ProfitReportDataItem fromMap(Map<String, Object> map) {
        ProfitReportDataItem profitReportDataItem = new ProfitReportDataItem();
        profitReportDataItem.setActualProfit(((BigDecimal) map.getOrDefault("actual_profit", 0)).setScale(2, RoundingMode.HALF_UP));
        profitReportDataItem.setPlannedProfit(((BigDecimal) map.getOrDefault("planned_profit", 0)).setScale(2, RoundingMode.HALF_UP));
        Project project = new Project();
        project.setId((int) map.getOrDefault("project_id", 0));
        project.setContractTitle((String) map.getOrDefault("contract_title", ""));
        profitReportDataItem.setProject(project);
        return profitReportDataItem;
    }
}
