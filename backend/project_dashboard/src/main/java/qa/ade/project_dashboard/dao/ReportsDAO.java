package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.*;

import java.util.List;

public interface ReportsDAO {
    List<ForecastCompletionReportDataItem> getForecastReport();

    ForecastCompletionReportDataItem getForecastReport(Project project);

    List<ProfitReportDataItem> getProfitReport();

    ProfitReportDataItem getProfitReport(Project project);

    List<PaymentReportDataItem> getPaymentReport();

    PaymentReportDataItem getPaymentReport(Project project);

    List<ProgressReportDataItem> getProgressReport();

    ProgressReportDataItem getProgressReport(Project project);
}
