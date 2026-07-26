package com.lakeatts.ftsprocurement.projectbudgetitem;
 

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectBudgetItemDAOImpl implements ProjectBudgetItemDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProjectBudgetItemDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ProjectBudgetItemDTO item) {
        String sql = "INSERT INTO fts.project_budget_items (id, project_id, item_name, quantity, unit_cost) " +
                     "VALUES (?, ?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                item.getId(),
                item.getProjectId(),
                item.getItemName(),
                item.getQuantity(),
                item.getUnitCost()
        );
        return rows > 0;
    }

    @Override
    public boolean update(ProjectBudgetItemDTO item) {
        String sql = "UPDATE fts.project_budget_items SET project_id=?, item_name=?, quantity=?, unit_cost=? " +
                     "WHERE id=?";
        int rows = jdbcTemplate.update(sql,
                item.getProjectId(),
                item.getItemName(),
                item.getQuantity(),
                item.getUnitCost(),
                item.getId()
        );
        return rows > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM fts.project_budget_items WHERE id=?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }

    @Override
    public ProjectBudgetItemDTO getById(Long id) {
        String sql = "SELECT * FROM fts.project_budget_items WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ProjectBudgetItemRowMapper(), id);
    }

    @Override
    public List<ProjectBudgetItemDTO> getByProjectId(Long projectId) {
        String sql = "SELECT * FROM fts.project_budget_items WHERE project_id=?";
        return jdbcTemplate.query(sql, new ProjectBudgetItemRowMapper(), projectId);
    }

    @Override
    public List<ProjectBudgetItemDTO> getAll() {
        String sql = "SELECT * FROM fts.project_budget_items ORDER BY id";
        return jdbcTemplate.query(sql, new ProjectBudgetItemRowMapper());
    }
}