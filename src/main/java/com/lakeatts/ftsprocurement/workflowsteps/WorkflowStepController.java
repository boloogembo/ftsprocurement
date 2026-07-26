package com.lakeatts.ftsprocurement.workflowsteps;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lakeatts.ftsprocurement.common.ApiResponse;

@RestController
@RequestMapping("/workflow-steps")
public class WorkflowStepController {

    private final WorkflowStepDAO dao;

    public WorkflowStepController(WorkflowStepDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> create(@RequestBody WorkflowStepDTO dto) {
        try {
            return ApiResponse.success("Workflow step created", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Failed to create step", e.getMessage());
        }
    }

    @PutMapping
    public ApiResponse<Integer> update(@RequestBody WorkflowStepDTO dto) {
        try {
            return ApiResponse.success("Workflow step updated", dao.update(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update step", e.getMessage());
        }
    }

    @DeleteMapping
    public ApiResponse<Integer> delete(@RequestParam Long id) {
        try {
            return ApiResponse.success("Workflow step deleted", dao.delete(id));
        } catch (Exception e) {
            return ApiResponse.fail("Failed to delete step", e.getMessage());
        }
    }

    @GetMapping("/by-id")
    public ApiResponse<WorkflowStepDTO> getById(@RequestParam Long id) {
        try {
            return ApiResponse.success("Success", dao.getById(id));
        } catch (Exception e) {
            return ApiResponse.fail("Error fetching step", e.getMessage());
        }
    }

    @GetMapping("/by-workflow")
    public ApiResponse<List<WorkflowStepDTO>> getByWorkflow(@RequestParam Long workflowId) {
        try {
            return ApiResponse.success("Success", dao.getByWorkflowId(workflowId));
        } catch (Exception e) {
            return ApiResponse.fail("Error fetching steps", e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<List<WorkflowStepDTO>> getAll() {
        try {
            return ApiResponse.success("Success", dao.getAll());
        } catch (Exception e) {
            return ApiResponse.fail("Error fetching steps", e.getMessage());
        }
    }
}