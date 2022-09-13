package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public class BudgetComponent {
    private String description;
    private BigDecimal cost;

    public BudgetComponent() {
        description = "";
        cost = new BigDecimal("0");
    }

    public BudgetComponent(String description, BigDecimal cost) {
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String toString() {
        return "BudgetComponent{" +
                "description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
