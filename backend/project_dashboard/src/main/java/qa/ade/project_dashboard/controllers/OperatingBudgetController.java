package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.model.OperatingBudget;
import qa.ade.project_dashboard.service.ProjectService;

@CrossOrigin(origins = "http://localhost:8080")
@ComponentScan("qa.ade.project_dashboard")
@RestController
public class OperatingBudgetController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/{projectId}/operatingBudget")
    public OperatingBudget operatingBudget(@PathVariable(value = "projectId") int projectId) {
        return projectService.getOperatingBudget(projectId);
    }

    @PostMapping("/project/{projectId}/operatingBudget")
    public void setOperatingBudget(@PathVariable(value = "projectId") int projectId, @RequestBody OperatingBudget budget) {
        projectService.setOperatingBudget(budget, projectId);
    }
}
