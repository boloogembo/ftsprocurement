
package com.lakeatts.ftsprocurement.approvalactions;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprovalActionRowMapper implements RowMapper<ApprovalActionDTO> {

    @Override
    public ApprovalActionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApprovalActionDTO dto = new ApprovalActionDTO();
        dto.setId(rs.getLong("id"));
        dto.setRequestId(rs.getLong("request_id"));
        dto.setStepId(rs.getLong("step_id"));
        dto.setDesignation(rs.getString("designation"));
        dto.setDenominationId(rs.getInt("denomination_id"));
        dto.setUserId((Long) rs.getObject("user_id"));
        dto.setAction(rs.getString("action"));
        dto.setWeight(rs.getBigDecimal("weight"));
        dto.setComment(rs.getString("comment"));
        if (rs.getTimestamp("created_at") != null) {
            dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return dto;
    }
}
