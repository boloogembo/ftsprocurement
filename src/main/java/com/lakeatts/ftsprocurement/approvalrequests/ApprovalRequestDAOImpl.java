package com.lakeatts.ftsprocurement.approvalrequests;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApprovalRequestDAOImpl implements ApprovalRequestDAO {

    private final JdbcTemplate jdbcTemplate;

    public ApprovalRequestDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(ApprovalRequestDTO dto) {

        String sql = """
            INSERT INTO fts.approval_requests (
                id,
                workflow_id,
                reference_number,
                organisation_id,
                amount,
                status,
                current_step_id,
                created_by,
                department_name,
                project_name,
                approval_deadline
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getWorkflowId(),
                dto.getReferenceNumber(),
                dto.getOrganisationId(),
                dto.getAmount(),
                dto.getStatus(),
                dto.getCurrentStepId(),
                dto.getCreatedBy(),
                dto.getDepartmentName(),
                dto.getProjectName(),
                dto.getApprovalDeadline()
        );
    }

    @Override
    public int updateStatus(Long id, String status) {

        String sql = """
            UPDATE fts.approval_requests
            SET status = ?
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql, status, id);
    }

    @Override
    public List<ApprovalRequestDTO> getAll() {

        String sql = """
            SELECT * 
            FROM fts.approval_requests
            ORDER BY created_at DESC
        """;

        return jdbcTemplate.query(sql, new ApprovalRequestRowMapper());
    }

    @Override
    public List<ApprovalRequestDTO> getByOrganisation(Long organisationId) {

        String sql = """
            SELECT *
            FROM fts.approval_requests
            WHERE organisation_id = ?
            ORDER BY created_at DESC
        """;

        return jdbcTemplate.query(sql, new ApprovalRequestRowMapper(), organisationId);
    }
}