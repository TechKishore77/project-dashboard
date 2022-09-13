package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.util.List;

public class OperatingBudget extends Budget {

    private List<OperatingBudgetComponent> components;

    protected BudgetType type() {
        return BudgetType.OperatingBudget;
    }

    public OperatingBudget() {
    }

    public OperatingBudget(long id, BigDecimal totalBudget, List<OperatingBudgetComponent> components) {
        super(id, totalBudget);
        this.components = components;
    }

    public List<OperatingBudgetComponent> getComponents() {
        return components;
    }

    public void setComponents(List<OperatingBudgetComponent> components) {
        this.components = components;
    }

    public String toString() {
        return "OperatingBudget{" +
                "components=" + components +
                ", totalBudget=" + getTotalBudget() +
                '}';
    }
}
