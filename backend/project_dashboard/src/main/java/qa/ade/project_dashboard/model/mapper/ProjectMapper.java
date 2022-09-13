package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        User user = new User();
        project.setId(rs.getLong("id"));
        project.setContractNo(rs.getString("contract_no"));
        project.setContractTitle(rs.getString("contract_title"));
        project.setClient(rs.getString("client"));
        project.setConsultant(rs.getString("consultant"));
        project.setTypeOfContract(rs.getString("type_of_contract"));
        project.setFormOfContract(rs.getString("form_of_contract"));
        project.setContractValue(rs.getBigDecimal("contract_value"));
        project.setStartDate(rs.getDate("start_date"));
        project.setCompletionDate(rs.getDate("completion_date"));
        project.setDuration(rs.getInt("duration"));
        project.setMaintenancePeriod(rs.getInt("maintenance_period"));
        project.setStatus(rs.getString("status"));
        project.setAddedBy(rs.getString("added_by"));
        return project;
    }
}
