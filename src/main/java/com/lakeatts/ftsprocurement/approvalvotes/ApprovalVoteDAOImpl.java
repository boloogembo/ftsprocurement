package com.lakeatts.ftsprocurement.approvalvotes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApprovalVoteDAOImpl implements ApprovalVoteDAO {

    private final JdbcTemplate jdbcTemplate;

    public ApprovalVoteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(ApprovalVoteDTO dto) {

        String sql = """
            INSERT INTO fts.approval_votes (
                id,
                request_id,
                step_id,
                voter_id,
                designation,
                organisation_id,
                vote,
                weight,
                comment
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getRequestId(),
                dto.getStepId(),
                dto.getVoterId(),
                dto.getDesignation(),
                dto.getOrganisationId(),
                dto.getVote(),
                dto.getWeight(),
                dto.getComment()
        );
    }

    @Override
    public List<ApprovalVoteDTO> getByRequestAndStep(Long requestId, Long stepId) {

        String sql = """
            SELECT *
            FROM fts.approval_votes
            WHERE request_id = ?
            AND step_id = ?
        """;

        return jdbcTemplate.query(sql,
                new ApprovalVoteRowMapper(),
                requestId,
                stepId
        );
    }

    @Override
    public Double getTotalWeight(Long requestId, Long stepId, String vote) {

        String sql = """
            SELECT COALESCE(SUM(weight), 0)
            FROM fts.approval_votes
            WHERE request_id = ?
            AND step_id = ?
            AND vote = ?
        """;

        return jdbcTemplate.queryForObject(sql,
                Double.class,
                requestId,
                stepId,
                vote
        );
    }
}