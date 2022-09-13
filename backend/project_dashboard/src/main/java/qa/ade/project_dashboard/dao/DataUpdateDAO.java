package qa.ade.project_dashboard.dao;

import qa.ade.project_dashboard.model.CommercialUpdateRequest;
import qa.ade.project_dashboard.model.ScheduleUpdateRequest;

public interface DataUpdateDAO {
    void scheduleUpdate(long projectId, ScheduleUpdateRequest request);

    void commercialUpdate(long projectId, CommercialUpdateRequest request);
}
