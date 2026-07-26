package com.lakeatts.ftsprocurement.departmentprojects;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentProjectRowMapper implements RowMapper<DepartmentProjectDTO> {

    @Override
    public DepartmentProjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        DepartmentProjectDTO dto = new DepartmentProjectDTO();

        dto.setId(rs.getLong("id"));
        dto.setOrganisationId(rs.getInt("organisation_id"));
        dto.setDepartmentName(rs.getString("department_name"));
        dto.setProjectName(rs.getString("project_name"));
        dto.setDescription(rs.getString("description"));
        dto.setCreatedBy(rs.getLong("created_by"));

        if (rs.getTimestamp("created_at") != null) {
            dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }

        return dto;
    }
}