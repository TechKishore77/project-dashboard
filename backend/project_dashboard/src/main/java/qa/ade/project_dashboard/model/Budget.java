package qa.ade.project_dashboard.model;

import java.math.BigDecimal;

public abstract class Budget {
    private long id;
    private BigDecimal totalBudget;

    abstract protected BudgetType type();

    public Budget() {
        id = 0;
        totalBudget = new BigDecimal("0");
    }

    public Budget(long id, BigDecimal totalBudget) {
        this.id = id;
        this.totalBudget = totalBudget;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", totalBudget=" + totalBudget +
                '}';
    }
}
