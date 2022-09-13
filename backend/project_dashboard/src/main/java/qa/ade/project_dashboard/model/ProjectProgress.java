package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class ProjectProgress {
    private Date dataDate;
    private BigDecimal progressPercentage;
    private Date forecastCompletionDate;

    public ProjectProgress(Date dataDate, BigDecimal progressPercentage, Date forecastCompletionDate) {
        this.dataDate = dataDate;
        this.progressPercentage = progressPercentage;
        this.forecastCompletionDate = forecastCompletionDate;
    }

    public ProjectProgress() {
        this.dataDate = Date.valueOf(LocalDate.EPOCH);
        this.progressPercentage = new BigDecimal("0");
        this.forecastCompletionDate = Date.valueOf(LocalDate.EPOCH);
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public BigDecimal getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(BigDecimal progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Date getForecastCompletionDate() {
        return forecastCompletionDate;
    }

    public void setForecastCompletionDate(Date forecastCompletionDate) {
        this.forecastCompletionDate = forecastCompletionDate;
    }

    public String toString() {
        return "ProjectProgress{" +
                "dataDate=" + dataDate +
                ", progressPercentage=" + progressPercentage +
                ", forecastCompletionDate=" + forecastCompletionDate +
                '}';
    }
}
