package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.model.AnticipatedBudget;
import qa.ade.project_dashboard.service.ProjectService;

@CrossOrigin(origins = "http://localhost:8080")
@ComponentScan("qa.ade.project_dashboard")
@RestController
public class AnticipatedBudgetController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/{projectId}/anticipatedBudget")
    public AnticipatedBudget anticipatedBudget(@PathVariable(value = "projectId") int projectId) {
        return projectService.getLatestAnticipatedBudget(projectId);
    }

    @PostMapping("/project/{projectId}/anticipatedBudget")
    public void addAnticipatedBudget(@PathVariable(value = "projectId") int projectId, @RequestBody AnticipatedBudget budget) {
        if (projectId != 0)
            projectService.addAnticipatedBudget(budget, projectId);
    }
}
