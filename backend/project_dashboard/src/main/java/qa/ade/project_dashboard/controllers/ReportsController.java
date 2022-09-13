package qa.ade.project_dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import qa.ade.project_dashboard.model.*;
import qa.ade.project_dashboard.service.ReportsService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@RestController
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @GetMapping("/project/{project}/report/summary")
    ProjectSummary summary() {
        return new ProjectSummary(
                LocalDate.now(),
                LocalDate.now(),
                new BigDecimal("0"),
                new BigDecimal("0"),
                LocalDate.now(),
                new Invoice(Date.valueOf(LocalDate.now()), new BigDecimal("0"), new BigDecimal("0")),
                new Invoice(Date.valueOf(LocalDate.now()), new BigDecimal("0"), new BigDecimal("0")),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        );
    }

    @GetMapping("/project/{projectId}/reports/forecastCompletion")
    ForecastCompletionReportDataItem forecastReport(@PathVariable(value = "projectId") long projectId) {
        Project project = new Project();
        project.setId(projectId);
        return reportsService.getForecastReport(project);
    }

    @GetMapping("/project/{projectId}/reports/progress")
    ProgressReportDataItem progressReport(@PathVariable(value = "projectId") long projectId) {
        Project project = new Project();
        project.setId(projectId);
        return reportsService.getProgressReport(project);
    }

    @GetMapping("/project/{projectId}/reports/payment")
    PaymentReportDataItem paymentReport(@PathVariable(value = "projectId") long projectId) {
        Project project = new Project();
        project.setId(projectId);
        return reportsService.getPaymentReport(project);
    }

    @GetMapping("/project/{projectId}/reports/profit")
    ProfitReportDataItem profitReport(@PathVariable(value = "projectId") long projectId) {
        Project project = new Project();
        project.setId(projectId);
        return reportsService.getProfitReport(project);
    }

    @GetMapping("/reports/forecastCompletion")
    List<ForecastCompletionReportDataItem> forecastReport() {
        return reportsService.getForecastReport();
    }

    @GetMapping("/reports/progress")
    List<ProgressReportDataItem> progressReport() {
        return reportsService.getProgressReport();
    }

    @GetMapping("/reports/payment")
    List<PaymentReportDataItem> paymentReport() {
        return reportsService.getPaymentReport();
    }

    @GetMapping("/reports/profit")
    List<ProfitReportDataItem> profitReport() {
        return reportsService.getProfitReport();
    }
}
