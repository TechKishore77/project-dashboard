package qa.ade.project_dashboard.excel;

import qa.ade.project_dashboard.exceptions.InvalidExcelFormatException;
import qa.ade.project_dashboard.model.OperatingBudget;
import qa.ade.project_dashboard.model.OperatingBudgetComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperatingBudgetParser {

    private static final String KEY_TOTAL = "Total";
    private static final String KEY_CONTRACT_VALUE = "Contract Value";
    private static final String KEY_PROFIT = "Profit";

    public static OperatingBudget parse(Object[][] sheet) {
        OperatingBudget operatingBudget = new OperatingBudget();
        List<OperatingBudgetComponent> budgetComponents = new ArrayList<>();
        int i = 1;
        for (; i < sheet.length; i++) {
            if (sheet[i].length < 2)
                throw new InvalidExcelFormatException("Operating Budget sheet not in the right format");
            String name = (String) sheet[i][0];
            if (name != null && !name.isBlank()) {
                name = name.trim();
                if (name.equals(KEY_TOTAL)) operatingBudget.setTotalBudget(BigDecimal.valueOf((double) sheet[i][1]));
                else if (!(name.equals(KEY_CONTRACT_VALUE) || name.equals(KEY_PROFIT))) {
                    BigDecimal cost = BigDecimal.valueOf((double) sheet[i][1]);
                    OperatingBudgetComponent budgetComponent = new OperatingBudgetComponent(name, cost);
                    budgetComponents.add(budgetComponent);
                }
            }
        }
        operatingBudget.setComponents(budgetComponents);
        return operatingBudget;
    }
}
