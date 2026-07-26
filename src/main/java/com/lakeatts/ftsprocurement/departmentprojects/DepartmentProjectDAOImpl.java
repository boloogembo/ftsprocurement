package com.lakeatts.ftsprocurement.departmentprojects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentProjectDAOImpl implements DepartmentProjectDAO {

    private final JdbcTemplate jdbcTemplate;

    public DepartmentProjectDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(DepartmentProjectDTO dto) {

        String sql = """
            INSERT INTO fts.department_projects (
                id,
                organisation_id,
                department_name,
                project_name,
                description,
                created_by
            )
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getOrganisationId(),
                dto.getDepartmentName(),
                dto.getProjectName(),
                dto.getDescription(),
                dto.getCreatedBy()
        );
    }

    @Override
    public int update(DepartmentProjectDTO dto) {

        String sql = """
            UPDATE fts.department_projects
            SET
                department_name = COALESCE(?, department_name),
                project_name = COALESCE(?, project_name),
                description = COALESCE(?, description)
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql,
                dto.getDepartmentName(),
                dto.getProjectName(),
                dto.getDescription(),
                dto.getId()
        );
    }

    @Override
    public int delete(Long id) {

        String sql = "DELETE FROM fts.department_projects WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<DepartmentProjectDTO> getByOrganisation(Integer organisationId) {

        String sql = """
            SELECT *
            FROM fts.department_projects
            WHERE organisation_id = ?
            ORDER BY created_at DESC
        """;

        return jdbcTemplate.query(sql,
                new DepartmentProjectRowMapper(),
                organisationId
        );
    }

    @Override
    public List<DepartmentProjectDTO> getByDepartment(Integer organisationId, String departmentName) {

        String sql = """
            SELECT *
            FROM fts.department_projects
            WHERE organisation_id = ?
            AND department_name ILIKE ?
            ORDER BY created_at DESC
        """;

        return jdbcTemplate.query(sql,
                new DepartmentProjectRowMapper(),
                organisationId,
                "%" + departmentName + "%"
        );
    }
}