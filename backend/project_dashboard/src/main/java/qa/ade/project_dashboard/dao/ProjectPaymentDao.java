package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.ProjectPayment;

import java.util.List;

public interface ProjectPaymentDao {

    long createProjectPayment(ProjectPayment projectPayment);

    boolean createProjectPayments(List<ProjectPayment> projectPayments);

    boolean editProjectPayment(ProjectPayment projectPayment);

    ProjectPayment getProjectPaymentById(long id);

    List<ProjectPayment> getProjectPaymentByProjectId(long projectId);
}
