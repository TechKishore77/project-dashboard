package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.WBSComponent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WBSComponentMapper implements RowMapper<WBSComponent> {
    public WBSComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
        WBSComponent wbsComponent = new WBSComponent();
        wbsComponent.setName(rs.getString("name"));
        wbsComponent.setPercent(rs.getBigDecimal("percent"));
        return wbsComponent;
    }
}
