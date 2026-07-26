package com.lakeatts.ftsprocurement.workflow;
 

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
@RequestMapping("/workflows")
public class WorkflowController {

    private final WorkflowDAO dao;

    public WorkflowController(WorkflowDAO dao) {
        this.dao = dao;
    }

    // 🔥 CREATE
    @PostMapping
    public ApiResponse<Integer> create(@RequestBody WorkflowDTO dto) {
        try {
            int result = dao.insert(dto);
            return ApiResponse.success("Workflow created successfully", result);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to create workflow", e.getMessage());
        }
    }

    // 🔥 UPDATE
    @PutMapping
    public ApiResponse<Integer> update(@RequestBody WorkflowDTO dto) {
        try {
            int result = dao.update(dto);
            return ApiResponse.success("Workflow updated successfully", result);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update workflow", e.getMessage());
        }
    }

    // 🔥 DELETE
    @DeleteMapping
    public ApiResponse<Integer> delete(@RequestParam Long id) {
        try {
            int result = dao.delete(id);
            return ApiResponse.success("Workflow deleted successfully", result);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to delete workflow", e.getMessage());
        }
    }

    // 🔥 GET BY ID
    @GetMapping("/by-id")
    public ApiResponse<WorkflowDTO> getById(@RequestParam Long id) {
        try {
            return ApiResponse.success("Success", dao.getById(id));
        } catch (Exception e) {
            return ApiResponse.fail("Failed to fetch workflow", e.getMessage());
        }
    }

    // 🔥 GET ALL
    @GetMapping
    public ApiResponse<List<WorkflowDTO>> getAll() {
        try {
            return ApiResponse.success("Success", dao.getAll());
        } catch (Exception e) {
            return ApiResponse.fail("Failed to fetch workflows", e.getMessage());
        }
    }
}