package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public class AnticipatedBudgetComponent extends BudgetComponent {
    public AnticipatedBudgetComponent() {
    }

    public AnticipatedBudgetComponent(String description, BigDecimal cost) {
        super(description, cost);
    }
}
