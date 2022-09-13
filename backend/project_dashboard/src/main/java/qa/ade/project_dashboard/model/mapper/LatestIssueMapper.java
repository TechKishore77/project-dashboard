package qa.ade.project_dashboard.model.mapper;

import qa.ade.project_dashboard.model.Issue;
import qa.ade.project_dashboard.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LatestIssueMapper extends IssueMapper {
    public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
        Issue issue = super.mapRow(rs, rowNum);
        if (issue != null) {
            Project project = issue.getProject();
            project.setContractTitle(rs.getString("contract_title"));
            project.setContractNo(rs.getString("contract_no"));
        }
        return issue;
    }
}
