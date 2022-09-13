package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.*;
import qa.ade.project_dashboard.model.AnticipatedBudget;
import qa.ade.project_dashboard.model.OperatingBudget;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectPayment;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    ProjectPropertyDAO projectPropertyDAO;

    @Autowired
    OperatingBudgetDAO operatingBudgetDAO;

    @Autowired
    AnticipatedBudgetDAO anticipatedBudgetDAO;

    @Autowired
    ProjectPaymentDao projectPaymentDao;

    public boolean addProject(Project project) {
        long newProjectId = projectDAO.createProject(project);
        if (newProjectId > 0) {
            project.setId(newProjectId);
            if (project.getProperties() != null && !project.getProperties().isEmpty()) {
                boolean ret = projectPropertyDAO.addProjectProperties(project, project.getProperties());
                if (!ret) projectDAO.deleteProject(project);
                return ret;
            }
        }
        return false;
    }

    public boolean editProject(Project project) {
        return projectDAO.editProject(project);
    }

    public Project getProject(long id) {
        return projectDAO.getProjectById(id);
    }

    public List<Project> getAllProjects() {
        List<Project> projects = projectDAO.getAllProjects();
        for (Project project : projects) {
            project.setProperties(projectPropertyDAO.getProjectProperties(project));
        }
        return projects;
    }

    public boolean deleteProject(Project project) {
        return projectDAO.deleteProject(project);
    }

    public OperatingBudget getOperatingBudget(Project project) {
        return operatingBudgetDAO.getOperatingBudget(project);
    }

    public OperatingBudget getOperatingBudget(long projectId) {
        return operatingBudgetDAO.getOperatingBudget(projectId);
    }

    public void setOperatingBudget(OperatingBudget operatingBudget, long projectId) {
        operatingBudgetDAO.updateOperatingBudget(projectId, operatingBudget);
    }

    public AnticipatedBudget getLatestAnticipatedBudget(long projectId) {
        return anticipatedBudgetDAO.getLatestAnticipatedBudget(projectId);
    }

    public void addAnticipatedBudget(AnticipatedBudget anticipatedBudget, long projectId) {
        anticipatedBudgetDAO.addAnticipatedBudget(projectId, anticipatedBudget);
    }

    public long addProjectPayment(ProjectPayment projectPayment) {
        return projectPaymentDao.createProjectPayment(projectPayment);
    }

    public boolean addProjectPayment(List<ProjectPayment> projectPayments) {
        return projectPaymentDao.createProjectPayments(projectPayments);
    }

    public boolean editProjectPayment(ProjectPayment projectPayment) {
        return projectPaymentDao.editProjectPayment(projectPayment);
    }

    public ProjectPayment getProjectPayment(long projectPaymentId) {
        return projectPaymentDao.getProjectPaymentById(projectPaymentId);
    }

    public List<ProjectPayment> getProjectPayments(long projectId) {
        return projectPaymentDao.getProjectPaymentByProjectId(projectId);
    }

}
