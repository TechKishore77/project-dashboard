package qa.ade.project_dashboard.service;

import qa.ade.project_dashboard.model.Issue;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface IssueService {
    List<Issue> getAllIssues(Project project);

    List<Issue> getAllIssues(long projectId);

    Issue getIssueById(long id);

    long createIssue(long projectId, Issue issue);

    long createIssue(Project project, Issue issue);

    boolean createIssues(Project project, List<Issue> issues);

    boolean editIssue(Issue issue);

    boolean deleteIssue(Issue issue);

    List<Issue> getLatestIssues();
}
