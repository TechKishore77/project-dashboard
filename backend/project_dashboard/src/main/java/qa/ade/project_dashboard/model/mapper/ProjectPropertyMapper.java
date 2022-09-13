package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.ProjectProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectPropertyMapper implements RowMapper<ProjectProperty> {

    public ProjectProperty mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectProperty property = new ProjectProperty();
        property.setId(rs.getLong("id"));
        property.setName(rs.getString("name"));
        property.setDescription(rs.getString("description"));
        property.setValue(rs.getString("value"));
        return property;
    }
}
