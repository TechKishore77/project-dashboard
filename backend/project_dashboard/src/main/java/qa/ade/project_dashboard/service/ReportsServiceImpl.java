package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.ReportsDAO;
import qa.ade.project_dashboard.model.*;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Service("reportsService")
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    ReportsDAO reportsDAO;

    public List<ForecastCompletionReportDataItem> getForecastReport() {
        return reportsDAO.getForecastReport();
    }

    public ForecastCompletionReportDataItem getForecastReport(Project project) {
        return reportsDAO.getForecastReport(project);
    }

    public List<ProfitReportDataItem> getProfitReport() {
        return reportsDAO.getProfitReport();
    }

    public ProfitReportDataItem getProfitReport(Project project) {
        return reportsDAO.getProfitReport(project);
    }

    public List<PaymentReportDataItem> getPaymentReport() {
        return reportsDAO.getPaymentReport();
    }

    public PaymentReportDataItem getPaymentReport(Project project) {
        return reportsDAO.getPaymentReport(project);
    }

    public List<ProgressReportDataItem> getProgressReport() {
        return reportsDAO.getProgressReport();
    }

    public ProgressReportDataItem getProgressReport(Project project) {
        return reportsDAO.getProgressReport(project);
    }
}
