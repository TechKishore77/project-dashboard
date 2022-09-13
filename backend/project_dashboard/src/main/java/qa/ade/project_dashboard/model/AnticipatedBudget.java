package qa.ade.project_dashboard.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class AnticipatedBudget extends Budget {

    private List<AnticipatedBudgetComponent> components;
    private Date dataDate;

    protected BudgetType type() {
        return BudgetType.AnticipatedBudget;
    }

    public AnticipatedBudget() {
    }

    public AnticipatedBudget(long id, BigDecimal totalBudget, List<AnticipatedBudgetComponent> components, Date dataDate) {
        super(id, totalBudget);
        this.components = components;
        this.dataDate = dataDate;
    }

    public List<AnticipatedBudgetComponent> getComponents() {
        return components;
    }

    public void setComponents(List<AnticipatedBudgetComponent> components) {
        this.components = components;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
