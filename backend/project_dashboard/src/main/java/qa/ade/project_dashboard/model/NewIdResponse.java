package qa.ade.project_dashboard.model;

public class NewIdResponse {
    long id;

    public NewIdResponse() {
    }

    public NewIdResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "NewIdResponse{" +
                "id=" + id +
                '}';
    }
}
