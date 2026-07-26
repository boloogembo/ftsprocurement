package com.lakeatts.ftsprocurement.approvalstepinstances;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApprovalStepInstanceDAOImpl implements ApprovalStepInstanceDAO {

    private final JdbcTemplate jdbcTemplate;

    public ApprovalStepInstanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(ApprovalStepInstanceDTO dto) {

        String sql = """
            INSERT INTO fts.approval_step_instances (
                id,
                request_id,
                step_id,
                status
            )
            VALUES (?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getRequestId(),
                dto.getStepId(),
                dto.getStatus()
        );
    }

    @Override
    public int updateStatus(Long id, String status) {

        String sql = """
            UPDATE fts.approval_step_instances
            SET status = ?, completed_at = NOW()
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql, status, id);
    }

    @Override
    public List<ApprovalStepInstanceDTO> getByRequestId(Long requestId) {

        String sql = """
            SELECT *
            FROM fts.approval_step_instances
            WHERE request_id = ?
            ORDER BY started_at ASC
        """;

        return jdbcTemplate.query(sql, new ApprovalStepInstanceRowMapper(), requestId);
    }
}