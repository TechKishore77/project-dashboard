package qa.ade.project_dashboard.service;

import qa.ade.project_dashboard.model.MonthlyActualValue;
import qa.ade.project_dashboard.model.MonthlyPlannedValue;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface PlannedAndActualValuesService {

    List<MonthlyPlannedValue> getMonthlyPlannedValues(Project project);

    List<MonthlyActualValue> getMonthlyActualValues(Project project);
}
