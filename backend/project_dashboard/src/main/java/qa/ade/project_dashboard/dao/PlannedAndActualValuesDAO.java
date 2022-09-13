package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.MonthlyActualValue;
import qa.ade.project_dashboard.model.MonthlyPlannedValue;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface PlannedAndActualValuesDAO {
    List<MonthlyPlannedValue> getMonthlyPlannedValues(Project project);

    List<MonthlyActualValue> getMonthlyActualValues(Project project);
}
