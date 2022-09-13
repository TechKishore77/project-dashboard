package qa.ade.project_dashboard.service;

import qa.ade.project_dashboard.model.*;

import java.util.List;

public interface ReportsService {
    List<ForecastCompletionReportDataItem> getForecastReport();

    ForecastCompletionReportDataItem getForecastReport(Project project);

    List<ProfitReportDataItem> getProfitReport();

    ProfitReportDataItem getProfitReport(Project project);

    List<PaymentReportDataItem> getPaymentReport();

    PaymentReportDataItem getPaymentReport(Project project);

    List<ProgressReportDataItem> getProgressReport();

    ProgressReportDataItem getProgressReport(Project project);
}
