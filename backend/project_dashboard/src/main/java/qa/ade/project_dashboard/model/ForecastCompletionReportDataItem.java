package qa.ade.project_dashboard.model;

import java.sql.Date;
import java.util.Map;

public class ForecastCompletionReportDataItem {
    Project project;
    Date forecastCompletionDate;
    Date plannedCompletionDate;
    int delayDays;

    public ForecastCompletionReportDataItem() {
    }

    public ForecastCompletionReportDataItem(Project project, Date forecastCompletionDate, Date plannedCompletionDate, int delayDays) {
        this.project = project;
        this.forecastCompletionDate = forecastCompletionDate;
        this.plannedCompletionDate = plannedCompletionDate;
        this.delayDays = delayDays;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getForecastCompletionDate() {
        return forecastCompletionDate;
    }

    public void setForecastCompletionDate(Date forecastCompletionDate) {
        this.forecastCompletionDate = forecastCompletionDate;
    }

    public Date getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public void setPlannedCompletionDate(Date plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public int getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(int delayDays) {
        this.delayDays = delayDays;
    }

    public static ForecastCompletionReportDataItem fromMap(Map<String, Object> map) {
        ForecastCompletionReportDataItem forecastCompletionReportDataItem = new ForecastCompletionReportDataItem();
        forecastCompletionReportDataItem.setDelayDays((int) map.getOrDefault("delay", 0));
        forecastCompletionReportDataItem.setPlannedCompletionDate((Date) map.getOrDefault("completion_date", null));
        forecastCompletionReportDataItem.setForecastCompletionDate((Date) map.getOrDefault("forecast_completion", forecastCompletionReportDataItem.getPlannedCompletionDate()));
        Project project = new Project();
        project.setId((int) map.getOrDefault("id", 0));
        project.setContractTitle((String) map.getOrDefault("contract_title", ""));
        project.setContractNo((String) map.getOrDefault("contract_no", ""));
        forecastCompletionReportDataItem.setProject(project);
        return forecastCompletionReportDataItem;
    }

    public String toString() {
        return "ForecastCompletionReportData{" +
                "project=" + project +
                ", forecastCompletionDate=" + forecastCompletionDate +
                ", plannedCompletionDate=" + plannedCompletionDate +
                ", delayDays=" + delayDays +
                '}';
    }
}
