package com.lakeatts.ftsprocurement.approvalvotes;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprovalVoteRowMapper implements RowMapper<ApprovalVoteDTO> {

    @Override
    public ApprovalVoteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        ApprovalVoteDTO dto = new ApprovalVoteDTO();

        dto.setId(rs.getLong("id"));
        dto.setRequestId(rs.getLong("request_id"));
        dto.setStepId(rs.getLong("step_id"));
        dto.setVoterId(rs.getLong("voter_id"));
        dto.setDesignation(rs.getString("designation"));
        dto.setOrganisationId(rs.getLong("organisation_id"));
        dto.setVote(rs.getString("vote"));
        dto.setWeight(rs.getBigDecimal("weight"));
        dto.setComment(rs.getString("comment"));

        if (rs.getTimestamp("voted_at") != null) {
            dto.setVotedAt(rs.getTimestamp("voted_at").toLocalDateTime());
        }

        return dto;
    }
}