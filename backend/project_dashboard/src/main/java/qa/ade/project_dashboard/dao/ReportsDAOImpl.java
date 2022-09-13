package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import qa.ade.project_dashboard.model.*;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("reportsDAO")
public class ReportsDAOImpl implements ReportsDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Object getSPResultSet(String storedProcName, long projectId) {
        List<SqlParameter> paramList = new ArrayList<>();
        paramList.add(new SqlParameter("projectId", Types.INTEGER));
        Map<String, Object> resultData = jdbcTemplate.call(connection -> {
            CallableStatement stmt = connection.prepareCall("{call " + storedProcName + "(?)}");
            stmt.setLong(1, projectId);
            return stmt;
        }, paramList);
        return resultData.get("#result-set-1");
    }

    public List<ForecastCompletionReportDataItem> getForecastReport() {
        List<Map<String, Object>> result = (List) getSPResultSet("get_forecast_completion_data", 0);
        return result.stream().map(ForecastCompletionReportDataItem::fromMap).collect(Collectors.toList());
    }

    public ForecastCompletionReportDataItem getForecastReport(Project project) {
        List<Map<String, Object>> result = (List) getSPResultSet("get_forecast_completion_data", project.getId());
        if (result.size() > 0) {
            return ForecastCompletionReportDataItem.fromMap(result.get(0));
        }
        return null;
    }

    public List<ProfitReportDataItem> getProfitReport() {
        List<Map<String, Object>> result = (List) getSPResultSet("get_project_profit_data", 0);
        return result.stream().map(ProfitReportDataItem::fromMap).collect(Collectors.toList());
    }

    public ProfitReportDataItem getProfitReport(Project project) {
        List<Map<String, Object>> result = (List) getSPResultSet("get_project_profit_data", project.getId());
        if (result.size() > 0) {
            return ProfitReportDataItem.fromMap(result.get(0));
        }
        return null;
    }

    public List<PaymentReportDataItem> getPaymentReport() {
        List<Map<String, Object>> result = (List) getSPResultSet("get_payment_data", 0);
        return result.stream().map(PaymentReportDataItem::fromMap).collect(Collectors.toList());
    }

    public PaymentReportDataItem getPaymentReport(Project project) {
        List<Map<String, Object>> result = (List) getSPResultSet("get_payment_data", project.getId());
        if (result.size() > 0) {
            return PaymentReportDataItem.fromMap(result.get(0));
        }
        return null;
    }

    public List<ProgressReportDataItem> getProgressReport() {
        List<Map<String, Object>> result = (List) getSPResultSet("get_project_progress_data", 0);
        return result.stream().map(ProgressReportDataItem::fromMap).collect(Collectors.toList());
    }

    public ProgressReportDataItem getProgressReport(Project project) {
        List<Map<String, Object>> result = (List) getSPResultSet("get_project_progress_data", project.getId());
        if (result.size() > 0) {
            return ProgressReportDataItem.fromMap(result.get(0));
        }
        return null;
    }
}
