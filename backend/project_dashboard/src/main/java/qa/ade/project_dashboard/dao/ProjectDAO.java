package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface ProjectDAO {
    Project getProjectById(long id);

    List<Project> getAllProjects();

    long createProject(Project project);

    boolean editProject(Project project);

    boolean deleteProject(Project project);

}
