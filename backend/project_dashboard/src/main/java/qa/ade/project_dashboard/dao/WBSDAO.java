package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.WBS;

public interface WBSDAO {
    WBS getWBS(long projectId);

    boolean updateWBS(Project project, WBS wbs);
}
