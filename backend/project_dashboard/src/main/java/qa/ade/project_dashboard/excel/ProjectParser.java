package qa.ade.project_dashboard.excel;

import qa.ade.project_dashboard.exceptions.InvalidExcelFormatException;
import qa.ade.project_dashboard.model.Project;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class ProjectParser {


    private static final String KEY_CONTRACT_NO = "Contract No.";
    private static final String KEY_CONTRACT_TITLE = "Contract Title";
    private static final String KEY_CLIENT = "Client";
    private static final String KEY_CONSULTANT = "Consultant";
    private static final String KEY_TYPE_OF_CONTRACT = "Type of Contract";
    private static final String KEY_FORM_OF_CONTRACT = "Form of Contract";
    private static final String KEY_CONTRACT_VALUE = "Contract Value";
    private static final String KEY_START_DATE = "Start Date";
    private static final String KEY_COMPLETION_DATE = "Completion Date";
    private static final String KEY_MAINTENANCE_PERIOD = "Maintenance Period";
    private static final String KEY_DURATION = "Duration";

    public static Project parse(Object[][] sheet) {
        Project project = new Project();
        Map<String, Object> projectDetailsMap = new HashMap<String, Object>();
        for (int i = 1; i < sheet.length; i++) {
            if (sheet[i].length < 2)
                throw new InvalidExcelFormatException("Project Details sheet not in the right format");
            String key = (String) sheet[i][0];
            projectDetailsMap.put(key.trim(), sheet[i][1]);
        }
        project.setContractNo((String) projectDetailsMap.getOrDefault(KEY_CONTRACT_NO, ""));
        project.setContractTitle((String) projectDetailsMap.getOrDefault(KEY_CONTRACT_TITLE, ""));
        project.setClient((String) projectDetailsMap.getOrDefault(KEY_CLIENT, ""));
        project.setConsultant((String) projectDetailsMap.getOrDefault(KEY_CONSULTANT, ""));
        project.setTypeOfContract((String) projectDetailsMap.getOrDefault(KEY_TYPE_OF_CONTRACT, ""));
        project.setFormOfContract((String) projectDetailsMap.getOrDefault(KEY_FORM_OF_CONTRACT, ""));
        project.setContractValue(BigDecimal.valueOf((double) projectDetailsMap.getOrDefault(KEY_CONTRACT_VALUE, 0)));
        java.util.Date startDate = (java.util.Date) projectDetailsMap.getOrDefault(KEY_START_DATE, new java.util.Date());
        Date startDateSql = new Date(startDate.getTime());
        project.setStartDate(startDateSql);
        java.util.Date completionDate = (java.util.Date) projectDetailsMap.getOrDefault(KEY_COMPLETION_DATE, new java.util.Date());
        Date completionDateSql = new Date(completionDate.getTime());
        project.setCompletionDate(completionDateSql);
        double maintenancePeriod = (double) projectDetailsMap.getOrDefault(KEY_MAINTENANCE_PERIOD, 0);
        project.setMaintenancePeriod((int) maintenancePeriod);
        project.setDuration((int) ChronoUnit.DAYS.between(startDateSql.toLocalDate(), completionDateSql.toLocalDate()) + 1);
//        project.setDuration((int) (double) projectDetailsMap.getOrDefault(KEY_DURATION, 0));
        System.out.println("Parsed Project: " + project);
        return project;
    }
}
