package com.lakeatts.ftsprocurement.workflowsteps;
 

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkflowStepDAOImpl implements WorkflowStepDAO {

    private final JdbcTemplate jdbcTemplate;

    public WorkflowStepDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(WorkflowStepDTO dto) {

        String sql = """
            INSERT INTO fts.workflow_steps (
                id,
                workflow_id,
                step_order,
                step_name,
                approval_mode,
                threshold_value,
                min_amount,
                max_amount,
                is_final_step
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                dto.getId(),
                dto.getWorkflowId(),
                dto.getStepOrder(),
                dto.getStepName(),
                dto.getApprovalMode(),
                dto.getThresholdValue(),
                dto.getMinAmount(),
                dto.getMaxAmount(),
                dto.getIsFinalStep()
        );
    }

    @Override
    public int update(WorkflowStepDTO dto) {

        String sql = """
            UPDATE fts.workflow_steps
            SET
                workflow_id = COALESCE(?, workflow_id),
                step_order = COALESCE(?, step_order),
                step_name = COALESCE(?, step_name),
                approval_mode = COALESCE(?, approval_mode),
                threshold_value = COALESCE(?, threshold_value),
                min_amount = COALESCE(?, min_amount),
                max_amount = COALESCE(?, max_amount),
                is_final_step = COALESCE(?, is_final_step)
            WHERE id = ?
        """;

        return jdbcTemplate.update(sql,
                dto.getWorkflowId(),
                dto.getStepOrder(),
                dto.getStepName(),
                dto.getApprovalMode(),
                dto.getThresholdValue(),
                dto.getMinAmount(),
                dto.getMaxAmount(),
                dto.getIsFinalStep(),
                dto.getId()
        );
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM fts.workflow_steps WHERE id = ?", id);
    }

    @Override
    public WorkflowStepDTO getById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM fts.workflow_steps WHERE id = ?",
                new WorkflowStepRowMapper(),
                id
        );
    }

    @Override
    public List<WorkflowStepDTO> getByWorkflowId(Long workflowId) {
        return jdbcTemplate.query(
                "SELECT * FROM fts.workflow_steps WHERE workflow_id = ? ORDER BY step_order ASC",
                new WorkflowStepRowMapper(),
                workflowId
        );
    }

    @Override
    public List<WorkflowStepDTO> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM fts.workflow_steps ORDER BY created_at DESC",
                new WorkflowStepRowMapper()
        );
    }
}