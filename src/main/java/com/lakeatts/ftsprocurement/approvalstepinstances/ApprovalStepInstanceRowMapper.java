package com.lakeatts.ftsprocurement.approvalstepinstances;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprovalStepInstanceRowMapper implements RowMapper<ApprovalStepInstanceDTO> {

    @Override
    public ApprovalStepInstanceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        ApprovalStepInstanceDTO dto = new ApprovalStepInstanceDTO();

        dto.setId(rs.getLong("id"));
        dto.setRequestId(rs.getLong("request_id"));
        dto.setStepId(rs.getLong("step_id"));
        dto.setStatus(rs.getString("status"));

        if (rs.getTimestamp("started_at") != null) {
            dto.setStartedAt(rs.getTimestamp("started_at").toLocalDateTime());
        }

        if (rs.getTimestamp("completed_at") != null) {
            dto.setCompletedAt(rs.getTimestamp("completed_at").toLocalDateTime());
        }

        return dto;
    }
}