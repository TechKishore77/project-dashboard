package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.InvoicingPlan;
import qa.ade.project_dashboard.model.Project;

public interface InvoicingPlanDAO {
    InvoicingPlan getInvoicingPlan(long projectId);

    boolean updateInvoicingPlan(Project project, InvoicingPlan invoicingPlan);
}
