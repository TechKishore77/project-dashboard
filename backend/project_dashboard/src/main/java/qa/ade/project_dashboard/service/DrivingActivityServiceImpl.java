package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.DrivingActivityDAO;
import qa.ade.project_dashboard.model.DrivingActivity;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

@Service("drivingActivityService")
@ComponentScan("qa.ade.project_dashboard")
public class DrivingActivityServiceImpl implements DrivingActivityService {

    @Autowired
    DrivingActivityDAO drivingActivityDAO;

    public List<DrivingActivity> getAllDrivingActivities(Project project) {
        return drivingActivityDAO.getAllDrivingActivities(project);
    }

    public List<DrivingActivity> getAllDrivingActivities(long projectId) {
        return drivingActivityDAO.getAllDrivingActivities(projectId);
    }

    public DrivingActivity getDrivingActivityById(long id) {
        return drivingActivityDAO.getDrivingActivityById(id);
    }

    public long createDrivingActivity(Project project, DrivingActivity drivingActivity) {
        return drivingActivityDAO.createDrivingActivity(project, drivingActivity);
    }

    public boolean createDrivingActivities(Project project, List<DrivingActivity> drivingActivities) {
        return drivingActivityDAO.createDrivingActivities(project, drivingActivities);
    }

    public boolean editDrivingActivity(DrivingActivity drivingActivity) {
        return drivingActivityDAO.editDrivingActivity(drivingActivity);
    }

    public boolean deleteDrivingActivity(DrivingActivity drivingActivity) {
        return drivingActivityDAO.deleteDrivingActivity(drivingActivity);
    }
}
