package com.lakeatts.ftsprocurement.workflow;
 

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkflowRowMapper implements RowMapper<WorkflowDTO> {

    @Override
    public WorkflowDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        WorkflowDTO dto = new WorkflowDTO();

        dto.setId(rs.getLong("id"));
        dto.setName(rs.getString("name"));
        dto.setDenominationId(rs.getInt("denomination_id"));
        dto.setOrganisationLevel((Integer) rs.getObject("organisation_level"));
        dto.setActive(rs.getBoolean("active"));
        dto.setCreatedAt(rs.getTimestamp("created_at") != null
                ? rs.getTimestamp("created_at").toLocalDateTime()
                : null);

        return dto;
    }
}