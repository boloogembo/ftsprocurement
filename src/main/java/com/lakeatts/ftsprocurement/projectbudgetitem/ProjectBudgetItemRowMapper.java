package com.lakeatts.ftsprocurement.projectbudgetitem;
 

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectBudgetItemRowMapper implements RowMapper<ProjectBudgetItemDTO> {
    @Override
    public ProjectBudgetItemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectBudgetItemDTO dto = new ProjectBudgetItemDTO();
        dto.setId(rs.getLong("id"));
        dto.setProjectId(rs.getLong("project_id"));
        dto.setItemName(rs.getString("item_name"));
        dto.setQuantity(rs.getInt("quantity"));
        dto.setUnitCost(rs.getBigDecimal("unit_cost"));
        dto.setTotalCost(rs.getBigDecimal("total_cost"));
        return dto;
    }
}