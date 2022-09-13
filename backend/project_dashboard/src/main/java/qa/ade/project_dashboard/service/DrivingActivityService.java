package qa.ade.project_dashboard.service;

import qa.ade.project_dashboard.model.DrivingActivity;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface DrivingActivityService {

    List<DrivingActivity> getAllDrivingActivities(Project project);

    List<DrivingActivity> getAllDrivingActivities(long projectId);

    DrivingActivity getDrivingActivityById(long id);

    long createDrivingActivity(Project project, DrivingActivity drivingActivity);

    boolean createDrivingActivities(Project project, List<DrivingActivity> drivingActivities);

    boolean editDrivingActivity(DrivingActivity drivingActivity);

    boolean deleteDrivingActivity(DrivingActivity drivingActivity);
}
