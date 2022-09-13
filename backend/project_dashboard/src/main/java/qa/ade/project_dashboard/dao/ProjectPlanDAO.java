package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectPlan;

public interface ProjectPlanDAO {
    ProjectPlan getProjectPlan(long projectId);

    boolean updateProjectPlan(Project project, ProjectPlan projectPlan);
}
