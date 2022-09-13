package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.*;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("dataUpdateDAO")
public class DataUpdateDAOImpl implements DataUpdateDAO {
    @Autowired
    ProjectProgressDAO projectProgressDAO;

    @Autowired
    DrivingActivityDAO drivingActivityDAO;

    @Autowired
    InvoiceDAO invoiceDAO;

    @Autowired
    AnticipatedBudgetDAO anticipatedBudgetDAO;

    @Transactional
    public void scheduleUpdate(long projectId, ScheduleUpdateRequest request) {
        if (request != null) {
            projectProgressDAO.addProjectProgressUpdate(new ProjectProgress(request.getDataDate(), request.getProgressPercent(), request.getForecastCompletionDate()), projectId);
            if (request.getDrivingActivities() != null && !request.getDrivingActivities().isEmpty()) {
                Project project = new Project();
                project.setId(projectId);
                drivingActivityDAO.createDrivingActivities(project, request.getDrivingActivities());
            }
        }
    }

    @Transactional
    public void commercialUpdate(long projectId, CommercialUpdateRequest request) {
        if (request != null) {
            Project project = new Project();
            project.setId(projectId);
            invoiceDAO.addInvoice(new Invoice(request.getInvoiceDate(), request.getLatestInvoiceAmount(), request.getCertifiedAmount()), project);
            if (request.getAnticipatedBudget() != null) {
                anticipatedBudgetDAO.addAnticipatedBudget(project, request.getAnticipatedBudget());
            }
        }
    }
}
