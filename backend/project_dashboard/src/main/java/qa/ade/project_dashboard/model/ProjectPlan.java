package qa.ade.project_dashboard.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlan {
    private long id;
    private String description;
    private List<ProjectPlanItem> planItems;

    public ProjectPlan() {
        this.id = 0;
        this.description = "";
        this.planItems = new ArrayList<>();
    }

    public ProjectPlan(long id, String description, List<ProjectPlanItem> planItems) {
        this.id = id;
        this.description = description;
        this.planItems = planItems;
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

    public List<ProjectPlanItem> getPlanItems() {
        return planItems;
    }

    public void setPlanItems(List<ProjectPlanItem> planItems) {
        this.planItems = planItems;
    }

    public String toString() {
        return "ProjectPlan{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", planItems=" + planItems +
                '}';
    }
}
