package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.AnticipatedBudget;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface AnticipatedBudgetDAO {

    List<AnticipatedBudget> getAnticipatedBudgets(Project project);

    AnticipatedBudget getLatestAnticipatedBudget(long projectId);

    List<AnticipatedBudget> getAnticipatedBudgets(long projectId);

    boolean addAnticipatedBudget(Project project, AnticipatedBudget budget);

    boolean addAnticipatedBudget(long projectId, AnticipatedBudget budget);
}
