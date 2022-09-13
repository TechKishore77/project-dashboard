package qa.ade.project_dashboard.model;

public class ProjectProperty {
    private long id;
    private String name;
    private String description;
    private String value;


    public ProjectProperty() {
        id = 0;
        name = "";
        description = "";
        value = "";
    }

    public ProjectProperty(long id, String name, String description, String value, int type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object getValue() {
        return value;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "ProjectProperty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
