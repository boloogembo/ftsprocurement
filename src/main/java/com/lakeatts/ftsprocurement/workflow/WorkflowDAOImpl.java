package com.lakeatts.ftsprocurement.workflow;
 

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkflowDAOImpl implements WorkflowDAO {

    private final JdbcTemplate jdbcTemplate;

    public WorkflowDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(WorkflowDTO dto) {

        String sql = """
            INSERT INTO fts.workflows (
                id,
                name,
                denomination_id,
                organisation_level,
                active
            ) VALUES (?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getName(),
                dto.getDenominationId(),
                dto.getOrganisationLevel(),
                dto.getActive()
        );
    }

    @Override
    public int update(WorkflowDTO dto) {

        String sql = """
            UPDATE fts.workflows
            SET
                name = COALESCE(?, name),
                denomination_id = COALESCE(?, denomination_id),
                organisation_level = COALESCE(?, organisation_level),
                active = COALESCE(?, active)
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDenominationId(),
                dto.getOrganisationLevel(),
                dto.getActive(),
                dto.getId()
        );
    }

    @Override
    public int delete(Long id) {

        String sql = "DELETE FROM fts.workflows WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public WorkflowDTO getById(Long id) {

        String sql = "SELECT * FROM fts.workflows WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new WorkflowRowMapper(), id);
    }

    @Override
    public List<WorkflowDTO> getAll() {

        String sql = "SELECT * FROM fts.workflows ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new WorkflowRowMapper());
    }
}
