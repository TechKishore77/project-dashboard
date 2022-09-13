package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.ProjectProgress;

import java.util.List;

public interface ProjectProgressDAO {
    List<ProjectProgress> getProjectProgress(Project project);

    List<ProjectProgress> getProjectProgress(long projectId);

    ProjectProgress getLatestProgressUpdate(Project project);

    ProjectProgress getLatestProgressUpdate(long projectId);

    long addProjectProgressUpdate(ProjectProgress projectProgress, Project project);

    long addProjectProgressUpdate(ProjectProgress projectProgress, long projectId);
}
