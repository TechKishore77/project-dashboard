package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.exceptions.BadRequestException;
import qa.ade.project_dashboard.model.Issue;
import qa.ade.project_dashboard.service.IssueService;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class IssueController {

    @Autowired
    IssueService issueService;

    @GetMapping("/project/{projectId}/issue")
    public List<Issue> issues(@PathVariable(value = "projectId") long projectId) {
        return issueService.getAllIssues(projectId);
    }

    @PostMapping("/project/{projectId}/issue")
    public void addIssue(@PathVariable(value = "projectId") long projectId, @RequestBody Issue issue) {
        issueService.createIssue(projectId, issue);
    }

    @GetMapping("/issue/{issueId}")
    public Issue issue(@PathVariable(value = "issueId") long issueId) {
        try {
            return issueService.getIssueById(issueId);
        } catch (EmptyResultDataAccessException excep) {
            throw new BadRequestException("Invalid issue ID");
        }
    }

    @PutMapping("/issue/{issueId}")
    public void editIssue(@RequestBody Issue issue, @PathVariable(value = "issueId") long issueId) {
        issue.setId(issueId);
        issueService.editIssue(issue);
    }

    @DeleteMapping("/issue/{issueId}")
    public void deleteIssue(@PathVariable(value = "issueId") long issueId) {
        Issue issue = new Issue();
        issue.setId(issueId);
        issueService.deleteIssue(issue);
    }

    @GetMapping("/issues")
    public List<Issue> latestIssues() {
        return issueService.getLatestIssues();
    }
}
