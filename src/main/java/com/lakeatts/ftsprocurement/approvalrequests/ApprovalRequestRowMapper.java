package com.lakeatts.ftsprocurement.approvalrequests;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprovalRequestRowMapper implements RowMapper<ApprovalRequestDTO> {

    @Override
    public ApprovalRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        ApprovalRequestDTO dto = new ApprovalRequestDTO();

        dto.setId(rs.getLong("id"));
        dto.setWorkflowId(rs.getLong("workflow_id"));
        dto.setReferenceNumber(rs.getString("reference_number"));
        dto.setOrganisationId(rs.getLong("organisation_id"));
        dto.setAmount(rs.getBigDecimal("amount"));
        dto.setStatus(rs.getString("status"));
        dto.setCurrentStepId((Long) rs.getObject("current_step_id"));
        dto.setCreatedBy((Long) rs.getObject("created_by"));

        if (rs.getTimestamp("created_at") != null) {
            dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }

        dto.setDepartmentName(rs.getString("department_name"));
        dto.setProjectName(rs.getString("project_name"));

        if (rs.getTimestamp("approval_deadline") != null) {
            dto.setApprovalDeadline(rs.getTimestamp("approval_deadline").toLocalDateTime());
        }

        return dto;
    }
}
