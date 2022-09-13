package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.OperatingBudget;
import qa.ade.project_dashboard.model.Project;

public interface OperatingBudgetDAO {
    OperatingBudget getOperatingBudget(Project project);

    OperatingBudget getOperatingBudget(long projectId);

    // Overwrite the previous operating budget
    boolean updateOperatingBudget(Project project, OperatingBudget budget);

    boolean updateOperatingBudget(long projectId, OperatingBudget budget);
}
