package qa.ade.project_dashboard.model;

import java.sql.Date;
import java.time.LocalDate;

public class DrivingActivity {
    private long id;
    private Date dataDate;
    private String name;
    private String status;
    private String issues;
    private String actionParty;
    private Project project;

    public DrivingActivity() {
        id = 0;
        dataDate = Date.valueOf(LocalDate.now());
        name = "";
        status = "";
        issues = "";
        actionParty = "";
        project = new Project();
    }

    public DrivingActivity(long id, Date dataDate, String name, String status, String issues, String actionParty, Project project) {
        this.id = id;
        this.dataDate = dataDate;
        this.name = name;
        this.status = status;
        this.issues = issues;
        this.actionParty = actionParty;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getIssues() {
        return issues;
    }

    public String getActionParty() {
        return actionParty;
    }

    public Project getProject() {
        return project;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public void setActionParty(String actionParty) {
        this.actionParty = actionParty;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String toString() {
        return "DrivingActivity{" +
                "id=" + id +
                ", dataDate=" + dataDate +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", issues='" + issues + '\'' +
                ", actionParty='" + actionParty + '\'' +
                ", project=" + project +
                '}';
    }
}
