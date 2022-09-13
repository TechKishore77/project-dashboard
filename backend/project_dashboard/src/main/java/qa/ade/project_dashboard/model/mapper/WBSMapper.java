package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.WBS;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WBSMapper implements RowMapper<WBS> {
    public WBS mapRow(ResultSet rs, int rowNum) throws SQLException {
        WBS wbs = new WBS();
        wbs.setId(rs.getLong("id"));
        return wbs;
    }
}
