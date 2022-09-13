package qa.ade.project_dashboard.service;

import qa.ade.project_dashboard.model.*;

public interface PlanUploadService {
    boolean updatePlan(Project project, InvoicingPlan invoicingPlan, ProjectPlan projectPlan, WBS wbs, OperatingBudget budget);
}
