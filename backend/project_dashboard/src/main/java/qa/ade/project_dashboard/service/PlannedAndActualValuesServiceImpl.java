package qa.ade.project_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import qa.ade.project_dashboard.dao.PlannedAndActualValuesDAO;
import qa.ade.project_dashboard.model.MonthlyActualValue;
import qa.ade.project_dashboard.model.MonthlyPlannedValue;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Service("plannedAndActualValuesService")
public class PlannedAndActualValuesServiceImpl implements PlannedAndActualValuesService {

    @Autowired
    PlannedAndActualValuesDAO plannedAndActualValuesDAO;

    public List<MonthlyPlannedValue> getMonthlyPlannedValues(Project project) {
        return plannedAndActualValuesDAO.getMonthlyPlannedValues(project);
    }

    public List<MonthlyActualValue> getMonthlyActualValues(Project project) {
        return plannedAndActualValuesDAO.getMonthlyActualValues(project);
    }
}
