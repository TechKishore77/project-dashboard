package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.WBS;
import qa.ade.project_dashboard.model.WBSComponent;
import qa.ade.project_dashboard.model.mapper.WBSComponentMapper;
import qa.ade.project_dashboard.model.mapper.WBSMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("wbsDAO")
public class WBSDAOImpl implements WBSDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_WBS = "SELECT * FROM WBS WHERE project = ?";
    private static final String SQL_GET_WBS_COMPONENT = "SELECT * FROM WBS_COMPONENT WHERE wbs = ?";
    private static final String SQL_INSERT_WBS = "INSERT INTO WBS(project) VALUES (?)";
    private static final String SQL_INSERT_WBS_COMPONENT = "INSERT INTO WBS_COMPONENT(name, percent, wbs) VALUES (?,?,?)";
    private static final String SQL_DELETE_WBS = "DELETE FROM WBS WHERE project = ?";

    public WBS getWBS(long projectId) {
        WBS wbs = jdbcTemplate.queryForObject(SQL_GET_WBS, new WBSMapper(), projectId);
        if (wbs != null) {
            List<WBSComponent> components = jdbcTemplate.query(SQL_GET_WBS_COMPONENT, new WBSComponentMapper(), wbs.getId());
            wbs.setComponents(components);
        }
        return wbs;
    }

    @Transactional
    public boolean updateWBS(Project project, WBS wbs) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_DELETE_WBS, new Object[]{project.getId()}, new int[]{Types.INTEGER});
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_WBS, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, project.getId());
            return ps;
        }, keyHolder);
        long newId = (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
        int[] argTypes = {Types.VARCHAR, Types.DECIMAL, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (WBSComponent component : wbs.getComponents()) {
            batchArgs.add(new Object[]{component.getName(), component.getPercent(), newId});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_WBS_COMPONENT, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }
}
