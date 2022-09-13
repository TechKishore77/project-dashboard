package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import qa.ade.project_dashboard.model.DrivingActivity;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.service.DrivingActivityService;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class DrivingActivityController {

    @Autowired
    DrivingActivityService drivingActivityService;

    @GetMapping("/project/{projectId}/drivingActivity")
    public List<DrivingActivity> drivingActivities(@PathVariable(value = "projectId") long projectId) {
        return drivingActivityService.getAllDrivingActivities(projectId);
    }

    @PostMapping("/project/{projectId}/drivingActivity")
    public void addDrivingActivity(@PathVariable(value = "projectId") long projectId, @RequestBody DrivingActivity drivingActivity) {
        Project project = new Project();
        project.setId(projectId);
        drivingActivityService.createDrivingActivity(project, drivingActivity);
    }

    @GetMapping("/project/{projectId}/drivingActivity/{drivingActivityId}")
    public DrivingActivity drivingActivity(@PathVariable(value = "drivingActivityId") long drivingActivityId, @PathVariable(value = "projectId") long projectId) {
        return drivingActivityService.getDrivingActivityById(drivingActivityId);
    }

    @PutMapping("/drivingActivity/{drivingActivityId}")
    public void editDrivingActivity(@RequestBody DrivingActivity drivingActivity, @PathVariable(value = "drivingActivityId") long drivingActivityId) {
        drivingActivity.setId(drivingActivityId);
        drivingActivityService.editDrivingActivity(drivingActivity);
    }
}
