package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.IssueDAO;
import qa.ade.project_dashboard.model.Issue;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

@Service("issueService")
@ComponentScan("qa.ade.project_dashboard")
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueDAO issueDAO;

    public List<Issue> getAllIssues(Project project) {
        return issueDAO.getAllIssues(project);
    }

    public List<Issue> getAllIssues(long projectId) {
        return issueDAO.getAllIssues(projectId);
    }

    public Issue getIssueById(long id) {
        return issueDAO.getIssueById(id);
    }

    public long createIssue(long projectId, Issue issue) {
        Project project = new Project();
        project.setId(projectId);
        return createIssue(project, issue);
    }

    public long createIssue(Project project, Issue issue) {
        return issueDAO.createIssue(project, issue);
    }

    public boolean createIssues(Project project, List<Issue> issues) {
        return issueDAO.createIssues(project, issues);
    }

    public boolean editIssue(Issue issue) {
        return issueDAO.editIssue(issue);
    }

    public boolean deleteIssue(Issue issue) {
        return issueDAO.deleteIssue(issue);
    }

    public List<Issue> getLatestIssues() {
        return issueDAO.getLatestIssues();
    }
}
