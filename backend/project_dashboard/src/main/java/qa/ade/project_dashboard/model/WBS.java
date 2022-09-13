package qa.ade.project_dashboard.model;

import java.util.ArrayList;
import java.util.List;

public class WBS {
    private long id;
    private List<WBSComponent> components;

    public WBS() {
        this.id = 0;
        this.components = new ArrayList<>();
    }

    public WBS(long id, List<WBSComponent> components) {
        this.id = id;
        this.components = components;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<WBSComponent> getComponents() {
        return components;
    }

    public void setComponents(List<WBSComponent> components) {
        this.components = components;
    }

    public String toString() {
        return "WBS{" +
                "id=" + id +
                ", components=" + components +
                '}';
    }
}
