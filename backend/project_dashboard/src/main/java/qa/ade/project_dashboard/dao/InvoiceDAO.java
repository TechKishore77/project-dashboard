package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.Invoice;
import qa.ade.project_dashboard.model.Project;

import java.util.List;

public interface InvoiceDAO {
    Invoice getInvoiceById(long id);

    long addInvoice(Invoice invoice, Project project);

    List<Invoice> getAllInvoices(Project project);

    List<Invoice> getAllInvoices(long projectId);
}
