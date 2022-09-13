package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import qa.ade.project_dashboard.model.MonthlyActualValue;
import qa.ade.project_dashboard.model.MonthlyPlannedValue;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.MonthlyActualValueMapper;
import qa.ade.project_dashboard.model.mapper.MonthlyPlannedValueMapper;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("plannedAndActualValuesDAO")
public class PlannedAndActualValuesDAOImpl implements PlannedAndActualValuesDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_MONTHLY_PLANNED_VALUES = "SELECT DATE_FORMAT(month_end, '%m/%y') month, PPI.cum_pv planned_value," +
            " PPI.cum_pv_percent planned_percentage FROM PROJECT_PLAN_ITEM PPI INNER JOIN (SELECT max(day) month_end, plan FROM " +
            "(SELECT * FROM PROJECT_PLAN_ITEM INNER JOIN PROJECT_PLAN on PROJECT_PLAN_ITEM.plan = PROJECT_PLAN.id) PP " +
            "WHERE project = ? GROUP BY plan, YEAR(day), MONTH(day)) PP\n" +
            "ON PPI.day = PP.month_end AND PPI.plan = PP.plan ORDER BY month_end";
    private static final String SQL_GET_MONTHLY_ACTUAL_VALUES = "SELECT DATE_FORMAT(month_end, '%m/%y') month,\n" +
            "            PP.progress_percentage * PP.contract_value / 100 actual_value, PP.progress_percentage actual_percentage FROM\n" +
            "            (SELECT PP.progress_percentage, P.contract_value, PP.data_date FROM PROJECT_PROGRESS PP INNER JOIN PROJECT P ON PP.project = P.id) PP INNER JOIN\n" +
            "            (SELECT max(data_date) month_end FROM PROJECT_PROGRESS WHERE project = ? GROUP BY YEAR(data_date),\n" +
            "            MONTH(data_date)) PP_MONTH_END ON PP.data_date = PP_MONTH_END.month_end ORDER BY month_end";

    public List<MonthlyPlannedValue> getMonthlyPlannedValues(Project project) {
        return jdbcTemplate.query(SQL_GET_MONTHLY_PLANNED_VALUES, new MonthlyPlannedValueMapper(), project.getId());
    }

    public List<MonthlyActualValue> getMonthlyActualValues(Project project) {
        return jdbcTemplate.query(SQL_GET_MONTHLY_ACTUAL_VALUES, new MonthlyActualValueMapper(), project.getId());
    }
}
