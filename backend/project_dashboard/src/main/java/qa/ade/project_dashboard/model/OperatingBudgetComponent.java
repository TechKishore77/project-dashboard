package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

// TODO: Override toString
public class OperatingBudgetComponent extends BudgetComponent {
    public OperatingBudgetComponent() {
    }

    public OperatingBudgetComponent(String description, BigDecimal cost) {
        super(description, cost);
    }
}
