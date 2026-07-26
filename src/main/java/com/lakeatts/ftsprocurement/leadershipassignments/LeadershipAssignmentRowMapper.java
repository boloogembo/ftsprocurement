package com.lakeatts.ftsprocurement.leadershipassignments;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadershipAssignmentRowMapper implements RowMapper<LeadershipAssignmentDTO> {

    @Override
    public LeadershipAssignmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        LeadershipAssignmentDTO dto = new LeadershipAssignmentDTO();

        dto.setId(rs.getLong("id"));
        dto.setDesignation(rs.getString("designation"));
        dto.setDenominationId(rs.getInt("denomination_id"));
        dto.setPersonnelId(rs.getLong("personnel_id"));
        dto.setOrganisationId(rs.getLong("organisation_id"));
        dto.setActive(rs.getBoolean("active"));

        return dto;
    }
}