package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.exceptions.BadRequestException;
import qa.ade.project_dashboard.model.NewIdResponse;
import qa.ade.project_dashboard.model.ProjectPayment;
import qa.ade.project_dashboard.service.ProjectService;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class ProjectPaymentController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/project/payment")
    public NewIdResponse addProjectPaymentSummery(@RequestBody ProjectPayment projectPayment) {
        long newId = 0;
        try {
            newId = projectService.addProjectPayment(projectPayment);
        } catch (Exception exception) {
            throw new BadRequestException("Invalid request");
        }
        if (newId == 0) {
            throw new BadRequestException("Invalid request");
        }
        return new NewIdResponse(newId);
    }

    @GetMapping("/project/payment/{projectPaymentId}")
    public ProjectPayment projectPayment(@PathVariable(value = "projectPaymentId") int projectPaymentId) {
        ProjectPayment projectPayment = null;
        try {
            projectPayment = projectService.getProjectPayment(projectPaymentId);
        } catch (EmptyResultDataAccessException emptyResultException) {
            throw new BadRequestException("project id invalid");
        }
        return projectPayment;
    }

    @PutMapping("/project/payment")
    public void editProjectPayment(@RequestBody ProjectPayment projectPayment) {
        projectService.editProjectPayment(projectPayment);
    }

    @GetMapping("/project/payments/{projectId}")
    public List<ProjectPayment> searchProjectPayment(@PathVariable(value = "projectId") int projectId) {
        return projectService.getProjectPayments(projectId);
    }

}
