package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.*;
import qa.ade.project_dashboard.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("planUploadService")
@ComponentScan("qa.ade.project_dashboard")
public class PlanUploadServiceImpl implements PlanUploadService {

    @Autowired
    InvoicingPlanDAO invoicingPlanDAO;

    @Autowired
    ProjectPlanDAO projectPlanDAO;

    @Autowired
    WBSDAO wbsdao;

    @Autowired
    OperatingBudgetDAO operatingBudgetDAO;

    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    AnticipatedBudgetDAO anticipatedBudgetDAO;

    public boolean updatePlan(Project project, InvoicingPlan invoicingPlan, ProjectPlan projectPlan, WBS wbs, OperatingBudget budget) {
        if (project.getId() == 0) {
            long newProjectId = projectDAO.createProject(project);
            project.setId(newProjectId);
            AnticipatedBudget anticipatedBudget = new AnticipatedBudget();
            List<AnticipatedBudgetComponent> components = new ArrayList<>();
            anticipatedBudget.setDataDate(Date.valueOf(LocalDate.now()));
            for (OperatingBudgetComponent component : budget.getComponents()) {
                AnticipatedBudgetComponent anticipatedBudgetComponent = new AnticipatedBudgetComponent();
                anticipatedBudgetComponent.setDescription(component.getDescription());
                anticipatedBudgetComponent.setCost(component.getCost());
                components.add(anticipatedBudgetComponent);
            }
            anticipatedBudget.setComponents(components);
            anticipatedBudget.setTotalBudget(budget.getTotalBudget());
            anticipatedBudgetDAO.addAnticipatedBudget(newProjectId, anticipatedBudget);
        }
        operatingBudgetDAO.updateOperatingBudget(project, budget);
        invoicingPlanDAO.updateInvoicingPlan(project, invoicingPlan);
        projectPlanDAO.updateProjectPlan(project, projectPlan);
        wbsdao.updateWBS(project, wbs);
        return true;
    }
}
