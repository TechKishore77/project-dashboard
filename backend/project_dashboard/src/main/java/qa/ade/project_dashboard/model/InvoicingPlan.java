package qa.ade.project_dashboard.model;

import java.util.ArrayList;
import java.util.List;

public class InvoicingPlan {
    private long id;
    private String description;
    private List<InvoicingPlanItem> invoices;

    public InvoicingPlan() {
        this.id = 0;
        description = "";
        invoices = new ArrayList<>();
    }

    public InvoicingPlan(long id, String description, List<InvoicingPlanItem> invoices) {
        this.id = id;
        this.description = description;
        this.invoices = invoices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InvoicingPlanItem> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoicingPlanItem> invoices) {
        this.invoices = invoices;
    }

    public String toString() {
        return "InvoicePlan{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", invoices=" + invoices +
                '}';
    }
}
