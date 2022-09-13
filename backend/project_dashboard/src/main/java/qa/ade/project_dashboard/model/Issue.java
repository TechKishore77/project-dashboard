package qa.ade.project_dashboard.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Issue {
    private long id;
    private Timestamp createdOn;
    private Date reportingDate;
    private String description;
    private Date plannedCloseDate;
    private String actionParty;
    private String status;
    private Project project;

    public Issue() {
        createdOn = Timestamp.valueOf(LocalDateTime.now());
        reportingDate = Date.valueOf(LocalDate.EPOCH);
        description = "";
        plannedCloseDate = Date.valueOf(LocalDate.EPOCH);
        actionParty = "";
        status = "Open";
        project = new Project();
    }

    public Issue(long id, Timestamp createdOn, Date reportingDate, String description, Date plannedCloseDate, String actionParty, String status, Project project) {
        this.id = id;
        this.createdOn = createdOn;
        this.reportingDate = reportingDate;
        this.description = description;
        this.plannedCloseDate = plannedCloseDate;
        this.actionParty = actionParty;
        this.status = status;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPlannedCloseDate() {
        return plannedCloseDate;
    }

    public void setPlannedCloseDate(Date plannedCloseDate) {
        this.plannedCloseDate = plannedCloseDate;
    }

    public String getActionParty() {
        return actionParty;
    }

    public void setActionParty(String actionParty) {
        this.actionParty = actionParty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", reportingDate=" + reportingDate +
                ", description='" + description + '\'' +
                ", plannedCloseDate=" + plannedCloseDate +
                ", actionParty='" + actionParty + '\'' +
                ", status='" + status + '\'' +
                ", project=" + project +
                '}';
    }
}
