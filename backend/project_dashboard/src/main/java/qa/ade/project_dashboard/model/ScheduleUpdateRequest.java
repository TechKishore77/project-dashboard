package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ScheduleUpdateRequest {
    private Date dataDate;
    private BigDecimal progressPercent;
    private Date forecastCompletionDate;
    private List<DrivingActivity> drivingActivities;

    public ScheduleUpdateRequest(Date dataDate, BigDecimal progressPercent, Date forecastCompletionDate, List<DrivingActivity> drivingActivities) {
        this.dataDate = dataDate;
        this.progressPercent = progressPercent;
        this.forecastCompletionDate = forecastCompletionDate;
        this.drivingActivities = drivingActivities;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public BigDecimal getProgressPercent() {
        return progressPercent;
    }

    public Date getForecastCompletionDate() {
        return forecastCompletionDate;
    }

    public List<DrivingActivity> getDrivingActivities() {
        return drivingActivities;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public void setProgressPercent(BigDecimal progressPercent) {
        this.progressPercent = progressPercent;
    }

    public void setForecastCompletionDate(Date forecastCompletionDate) {
        this.forecastCompletionDate = forecastCompletionDate;
    }

    public void setDrivingActivities(List<DrivingActivity> drivingActivities) {
        this.drivingActivities = drivingActivities;
    }

    public String toString() {
        return "ScheduleUpdateRequest{" +
                "dataDate=" + dataDate +
                ", progressPercent=" + progressPercent +
                ", forecastCompletionDate=" + forecastCompletionDate +
                ", drivingActivities=" + drivingActivities +
                '}';
    }
}
