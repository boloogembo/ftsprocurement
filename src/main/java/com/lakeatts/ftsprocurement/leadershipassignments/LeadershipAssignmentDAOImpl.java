package com.lakeatts.ftsprocurement.leadershipassignments;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeadershipAssignmentDAOImpl implements LeadershipAssignmentDAO {

    private final JdbcTemplate jdbcTemplate;

    public LeadershipAssignmentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(LeadershipAssignmentDTO dto) {

        String sql = """
            INSERT INTO fts.leadership_assignments (
                id,
                designation,
                denomination_id,
                personnel_id,
                organisation_id,
                active
            )
            VALUES (?, ?, ?, ?, ?, COALESCE(?, true))
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getDesignation(),
                dto.getDenominationId(),
                dto.getPersonnelId(),
                dto.getOrganisationId(),
                dto.getActive()
        );
    }

    @Override
    public int update(LeadershipAssignmentDTO dto) {

        String sql = """
            UPDATE fts.leadership_assignments
            SET
                designation = COALESCE(?, designation),
                denomination_id = COALESCE(?, denomination_id),
                personnel_id = COALESCE(?, personnel_id),
                organisation_id = COALESCE(?, organisation_id),
                active = COALESCE(?, active)
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql,
                dto.getDesignation(),
                dto.getDenominationId(),
                dto.getPersonnelId(),
                dto.getOrganisationId(),
                dto.getActive(),
                dto.getId()
        );
    }

    @Override
    public int deactivate(Long id) {

        String sql = """
            UPDATE fts.leadership_assignments
            SET active = false
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int delete(Long id) {

        String sql = "DELETE FROM fts.leadership_assignments WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<LeadershipAssignmentDTO> getByOrganisation(Long organisationId) {

        String sql = """
            SELECT *
            FROM fts.leadership_assignments
            WHERE organisation_id = ?
            ORDER BY id DESC
        """;

        return jdbcTemplate.query(sql,
                new LeadershipAssignmentRowMapper(),
                organisationId
        );
    }

    @Override
    public List<LeadershipAssignmentDTO> getByDesignation(String designation, Integer denominationId) {

        String sql = """
            SELECT *
            FROM fts.leadership_assignments
            WHERE designation ILIKE ?
            AND denomination_id = ?
            AND active = true
        """;

        return jdbcTemplate.query(sql,
                new LeadershipAssignmentRowMapper(),
                "%" + designation + "%",
                denominationId
        );
    }
}