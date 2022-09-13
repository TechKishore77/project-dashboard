package qa.ade.project_dashboard.service;

import qa.ade.project_dashboard.model.AnticipatedBudget;
import qa.ade.project_dashboard.model.OperatingBudget;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectPayment;

import java.util.List;

public interface ProjectService {
    public boolean addProject(Project project);

    public boolean editProject(Project project);

    public Project getProject(long id);

    public List<Project> getAllProjects();

    public boolean deleteProject(Project project);

    public OperatingBudget getOperatingBudget(Project project);

    public OperatingBudget getOperatingBudget(long projectId);

    public void setOperatingBudget(OperatingBudget operatingBudget, long projectId);

    public AnticipatedBudget getLatestAnticipatedBudget(long projectId);

    public void addAnticipatedBudget(AnticipatedBudget anticipatedBudget, long projectId);

    long addProjectPayment(ProjectPayment projectPayment);

    boolean addProjectPayment(List<ProjectPayment> projectPayments);

    boolean editProjectPayment(ProjectPayment projectPayment);

    ProjectPayment getProjectPayment(long projectPaymentId);

    List<ProjectPayment> getProjectPayments(long projectId);

}
