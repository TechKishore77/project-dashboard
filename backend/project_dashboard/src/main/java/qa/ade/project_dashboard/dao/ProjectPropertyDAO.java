package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectProperty;

import java.util.List;

public interface ProjectPropertyDAO {
    public boolean addProjectProperties(Project project, List<ProjectProperty> properties);

    public boolean addProjectProperties(long projectId, List<ProjectProperty> properties);

    public boolean editProjectProperties(Project project, List<ProjectProperty> properties);

    public boolean editProjectProperties(long projectId, List<ProjectProperty> properties);

    public boolean deleteProjectProperties(Project project, List<ProjectProperty> properties);

    public boolean deleteProjectProperties(long projectId, List<ProjectProperty> properties);

    public List<ProjectProperty> getProjectProperties(Project project);

    public List<ProjectProperty> getProjectProperties(long projectId);
}
