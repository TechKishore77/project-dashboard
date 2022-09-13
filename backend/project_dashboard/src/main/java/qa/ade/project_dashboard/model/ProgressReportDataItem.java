package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Map;

public class ProgressReportDataItem {
    Project project;
    BigDecimal actualProgress;
    BigDecimal plannedProgress;
    Date dataDate;

    public ProgressReportDataItem() {
    }

    public ProgressReportDataItem(Project project, BigDecimal actualProgress, BigDecimal plannedProgress, Date dataDate) {
        this.project = project;
        this.actualProgress = actualProgress;
        this.plannedProgress = plannedProgress;
        this.dataDate = dataDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getActualProgress() {
        return actualProgress;
    }

    public void setActualProgress(BigDecimal actualProgress) {
        this.actualProgress = actualProgress;
    }

    public BigDecimal getPlannedProgress() {
        return plannedProgress;
    }

    public void setPlannedProgress(BigDecimal plannedProgress) {
        this.plannedProgress = plannedProgress;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String toString() {
        return "ProgressReportDataItem{" +
                "project=" + project +
                ", actualProgress=" + actualProgress +
                ", plannedProgress=" + plannedProgress +
                ", dataDate=" + dataDate +
                '}';
    }

    public static ProgressReportDataItem fromMap(Map<String, Object> map) {
        ProgressReportDataItem progressReportDataItem = new ProgressReportDataItem();
        progressReportDataItem.setActualProgress(((BigDecimal) map.getOrDefault("actual_progress", new BigDecimal("0"))).setScale(2, RoundingMode.HALF_UP));
        progressReportDataItem.setPlannedProgress(((BigDecimal) map.getOrDefault("planned_progress", new BigDecimal("0"))).setScale(2, RoundingMode.HALF_UP));
        progressReportDataItem.setDataDate((Date) map.getOrDefault("data_date", null));
        Project project = new Project();
        project.setId((int) map.getOrDefault("pid", 0));
        project.setContractTitle((String) map.getOrDefault("contract_title", ""));
        progressReportDataItem.setProject(project);
        return progressReportDataItem;
    }
}
