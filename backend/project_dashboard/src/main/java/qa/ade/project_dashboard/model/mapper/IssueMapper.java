package qa.ade.project_dashboard.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import qa.ade.project_dashboard.model.Issue;
import qa.ade.project_dashboard.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueMapper implements RowMapper<Issue> {
    public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
        Issue issue = new Issue();
        issue.setId(rs.getLong("id"));
        issue.setCreatedOn(rs.getTimestamp("created_on"));
        issue.setReportingDate(rs.getDate("reporting_date"));
        issue.setDescription(rs.getString("description"));
        issue.setPlannedCloseDate(rs.getDate("planned_close_date"));
        issue.setActionParty(rs.getString("action_party"));
        issue.setStatus(rs.getString("status"));
        Project project = new Project();
        project.setId(rs.getLong("project"));
        issue.setProject(project);
        return issue;
    }
}
