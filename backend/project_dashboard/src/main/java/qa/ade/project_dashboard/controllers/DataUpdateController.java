package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qa.ade.project_dashboard.dao.DataUpdateDAO;
import qa.ade.project_dashboard.model.CommercialUpdateRequest;
import qa.ade.project_dashboard.model.ScheduleUpdateRequest;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class DataUpdateController {

    @Autowired
    DataUpdateDAO dataUpdateDAO;

    @PostMapping("/project/{projectId}/scheduleUpdate")
    public void scheduleUpdate(@PathVariable(value = "projectId") long projectId, @RequestBody ScheduleUpdateRequest request) {
        dataUpdateDAO.scheduleUpdate(projectId, request);
    }

    @PostMapping("/project/{projectId}/commercialUpdate")
    public void commercialUpdate(@PathVariable(value = "projectId") long projectId, @RequestBody CommercialUpdateRequest request) {
        dataUpdateDAO.commercialUpdate(projectId, request);
    }
}
