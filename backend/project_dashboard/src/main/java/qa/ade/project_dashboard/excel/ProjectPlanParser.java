package qa.ade.project_dashboard.excel;

import qa.ade.project_dashboard.model.ProjectPlan;
import qa.ade.project_dashboard.model.ProjectPlanItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProjectPlanParser implements ExcelParser {
    public static ProjectPlan parse(Object[][] sheet) {
        ProjectPlan projectPlan = new ProjectPlan();
        boolean exitOuterFor = false;
        List<ProjectPlanItem> projectPlanItems = new ArrayList<>();
        for (int i = 1; i < sheet.length; i++) {
            Object[] row = sheet[i];
            if (row[0] == null || row[1] == null || row[2] == null || (row[0] instanceof String && ((String) row[0]).isBlank()))
                exitOuterFor = true;
            java.util.Date date = (java.util.Date) row[0];
            Double plannedValue = (Double) row[1];
            Double cumulativePV = (Double) row[2];
            Double cumulativePVPercent = (Double) row[3];
            cumulativePVPercent = cumulativePVPercent * 100;
            ProjectPlanItem projectPlanItem = new ProjectPlanItem();
            projectPlanItem.setDay(new java.sql.Date(date.getTime()));
            projectPlanItem.setPlannedValue(BigDecimal.valueOf(plannedValue));
            projectPlanItem.setCumulativePV(BigDecimal.valueOf(cumulativePV));
            projectPlanItem.setCumulativePVPercent(BigDecimal.valueOf(cumulativePVPercent));

            projectPlanItems.add(projectPlanItem);
            if (exitOuterFor) break;
        }
        projectPlan.setPlanItems(projectPlanItems);
        return projectPlan;
    }
}
