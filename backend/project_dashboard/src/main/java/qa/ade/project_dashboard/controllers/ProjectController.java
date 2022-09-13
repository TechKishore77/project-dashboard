package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.exceptions.BadRequestException;
import qa.ade.project_dashboard.exceptions.ConflictException;
import qa.ade.project_dashboard.exceptions.DatabaseQueryException;
import qa.ade.project_dashboard.exceptions.TableDoesNotExistException;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.service.ProjectService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@ComponentScan("qa.ade.project_dashboard")
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return projectService.getAllProjects();
    }

    @PostMapping("/project")
    void addProjectInfo(@RequestBody Project project) {
        boolean ret = false;
        try {
            ret = projectService.addProject(project);
        } catch (DuplicateKeyException exception) {
            projectService.deleteProject(project);
            throw new ConflictException("Project with same Contract No. already exists.");
        } catch (TableDoesNotExistException exception) {
            projectService.deleteProject(project);
            throw new DatabaseQueryException();
        }
        if (!ret) {
            throw new BadRequestException("Invalid request");
        }
    }

    @GetMapping("/project/{projectId}")
    public Project projectInfo(@PathVariable(value = "projectId") int projectId) {
        try {
            return projectService.getProject(projectId);
        } catch (EmptyResultDataAccessException emptyResultException) {
            throw new BadRequestException("project id invalid");
        }
    }

    @PutMapping("/project/{projectId}")
    public void editProject(@PathVariable(value = "projectId") int projectId, @RequestBody Project project) {
        projectService.editProject(project);
    }

    @DeleteMapping("/project/{projectId}")
    public void deleteProject(@PathVariable(value = "projectId") long projectId) {
        Project project = new Project();
        project.setId(projectId);
        projectService.deleteProject(project);
    }
}
