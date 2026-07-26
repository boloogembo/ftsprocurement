
package com.lakeatts.ftsprocurement.approvalactions;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ApprovalActionDAOImpl implements ApprovalActionDAO {

    private final JdbcTemplate jdbcTemplate;

    public ApprovalActionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(ApprovalActionDTO dto) {
        String sql = """
            INSERT INTO fts.approval_actions (
                id, request_id, step_id, designation, denomination_id,
                user_id, action, weight, comment
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getRequestId(),
                dto.getStepId(),
                dto.getDesignation(),
                dto.getDenominationId(),
                dto.getUserId(),
                dto.getAction(),
                dto.getWeight(),
                dto.getComment()
        );
    }

    @Override
    public List<ApprovalActionDTO> getByRequestId(Long requestId) {
        String sql = "SELECT * FROM fts.approval_actions WHERE request_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new ApprovalActionRowMapper(), requestId);
    }

    @Override
    public List<ApprovalActionDTO> getAll() {
        return jdbcTemplate.query("SELECT * FROM fts.approval_actions", new ApprovalActionRowMapper());
    }
}
