package qa.ade.project_dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qa.ade.project_dashboard.model.Issue;
import qa.ade.project_dashboard.model.Project;
import qa.ade.project_dashboard.model.mapper.IssueMapper;
import qa.ade.project_dashboard.model.mapper.LatestIssueMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ComponentScan("qa.ade.project_dashboard")
@Repository
@Qualifier("issueDAO")
public class IssueDAOImpl implements IssueDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_ISSUES = "SELECT * FROM ISSUE WHERE project=?";
    private static final String SQL_GET_ISSUE_BY_ID = "SELECT * FROM ISSUE WHERE id = ?";
    private static final String SQL_GET_LATEST_ISSUES = "SELECT ISSUE.*, PROJECT.id projectId, PROJECT.contract_no, PROJECT.contract_title " +
            "FROM ISSUE INNER JOIN PROJECT ON issue.project = project.id " +
            "ORDER BY issue.created_on DESC LIMIT 10;";
    private static final String SQL_INSERT_ISSUE = "INSERT INTO ISSUE(reporting_date, description, planned_close_date, action_party, status, project) VALUES (?,?,?,?,?,?)";
    private static final String SQL_DELETE_ISSUE = "DELETE FROM ISSUE WHERE id = ?";
    private static final String SQL_UPDATE_STATUS = "UPDATE ISSUE SET status = ? WHERE id = ?";
    private static final String SQL_UPDATE_ISSUE = "UPDATE ISSUE SET reporting_date = ?, description = ?, planned_close_date = ?, " +
            "action_party = ?, status = ? WHERE id = ?";

    public List<Issue> getAllIssues(Project project) {
        return getAllIssues(project.getId());
    }

    public List<Issue> getAllIssues(long projectId) {
        return jdbcTemplate.query(SQL_GET_ALL_ISSUES, new IssueMapper(), projectId);
    }

    public Issue getIssueById(long id) {
        return jdbcTemplate.queryForObject(SQL_GET_ISSUE_BY_ID, new IssueMapper(), id);
    }

    @Transactional
    public long createIssue(Project project, Issue issue) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ISSUE, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, issue.getReportingDate());
            ps.setString(2, issue.getDescription());
            ps.setDate(3, issue.getPlannedCloseDate());
            ps.setString(4, issue.getActionParty());
            ps.setString(5, issue.getStatus());
            ps.setLong(6, project.getId());
            return ps;
        }, keyHolder);
        return (keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue());
    }

    @Transactional
    public boolean createIssues(Project project, List<Issue> issues) {
        int[] argTypes = {Types.DATE, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        List<Object[]> batchArgs = new ArrayList<>();
        for (Issue issue : issues) {
            batchArgs.add(new Object[]{issue.getReportingDate(), issue.getDescription(), issue.getPlannedCloseDate(), issue.getActionParty(), issue.getStatus(), project.getId()});
        }
        int[] returns = jdbcTemplate.batchUpdate(SQL_INSERT_ISSUE, batchArgs, argTypes);
        for (int ret : returns) {
            if (ret == 0) return false;
        }
        return true;
    }

    @Transactional
    public boolean setIssueStatus(Issue issue) {
        return jdbcTemplate.update(SQL_UPDATE_STATUS, issue.getStatus(), issue.getId()) > 0;
    }

    @Transactional
    public boolean editIssue(Issue issue) {
        return jdbcTemplate.update(SQL_UPDATE_ISSUE,
                new Object[]{
                        issue.getReportingDate(),
                        issue.getDescription(),
                        issue.getPlannedCloseDate(),
                        issue.getActionParty(),
                        issue.getStatus(),
                        issue.getId()
                },
                new int[]{
                        Types.DATE,
                        Types.VARCHAR,
                        Types.DATE,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.INTEGER
                }
        ) > 0;
    }

    @Transactional
    public boolean deleteIssue(Issue issue) {
        return jdbcTemplate.update(SQL_DELETE_ISSUE, issue.getId()) > 0;
    }

    public List<Issue> getLatestIssues() {
        return jdbcTemplate.query(SQL_GET_LATEST_ISSUES, new LatestIssueMapper());
    }
}
