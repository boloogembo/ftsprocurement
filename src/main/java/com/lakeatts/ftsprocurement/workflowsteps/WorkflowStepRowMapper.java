package com.lakeatts.ftsprocurement.workflowsteps;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WorkflowStepRowMapper implements RowMapper<WorkflowStepDTO> {

    @Override
    public WorkflowStepDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        WorkflowStepDTO dto = new WorkflowStepDTO();

        dto.setId(rs.getLong("id"));
        dto.setWorkflowId(rs.getLong("workflow_id"));
        dto.setStepOrder(rs.getInt("step_order"));
        dto.setStepName(rs.getString("step_name"));
        dto.setApprovalMode(rs.getString("approval_mode"));
        dto.setThresholdValue(rs.getBigDecimal("threshold_value"));
        dto.setMinAmount(rs.getBigDecimal("min_amount"));
        dto.setMaxAmount(rs.getBigDecimal("max_amount"));
        dto.setIsFinalStep(rs.getBoolean("is_final_step"));

        if (rs.getTimestamp("created_at") != null) {
            dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }

        return dto;
    }
}