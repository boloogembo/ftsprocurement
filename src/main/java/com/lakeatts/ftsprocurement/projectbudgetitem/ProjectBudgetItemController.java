package com.lakeatts.ftsprocurement.projectbudgetitem;  

import com.lakeatts.ftsprocurement.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-budget-items")
public class ProjectBudgetItemController {

    private final ProjectBudgetItemDAO projectBudgetItemDAO;

    @Autowired
    public ProjectBudgetItemController(ProjectBudgetItemDAO projectBudgetItemDAO) {
        this.projectBudgetItemDAO = projectBudgetItemDAO;
    }

    // ✅ Get all items
    @GetMapping("/all")
    public ApiResponse<List<ProjectBudgetItemDTO>> getAll() {
        List<ProjectBudgetItemDTO> items = projectBudgetItemDAO.getAll();
        return ApiResponse.success("Fetched all project budget items", items);
    }

    // ✅ Get by ID
    @GetMapping
    public ApiResponse<ProjectBudgetItemDTO> getById(@RequestParam Long id) {
        ProjectBudgetItemDTO item = projectBudgetItemDAO.getById(id);
        if (item != null) {
            return ApiResponse.success("Item found", item);
        } else {
            return ApiResponse.fail("Item not found", "No record for ID: " + id);
        }
    }

    // ✅ Get by Project ID
    @GetMapping("/by-project")
    public ApiResponse<List<ProjectBudgetItemDTO>> getByProjectId(@RequestParam Long projectId) {
        List<ProjectBudgetItemDTO> items = projectBudgetItemDAO.getByProjectId(projectId);
        return ApiResponse.success("Fetched items for project " + projectId, items);
    }

    // ✅ Add a new item
    @PostMapping("/add")
    public ApiResponse<String> add(@RequestBody ProjectBudgetItemDTO item) {
        boolean success = projectBudgetItemDAO.add(item);
        if (success) {
            return ApiResponse.success("Item added successfully", null);
        } else {
            return ApiResponse.fail("Failed to add item", "Database insert failed");
        }
    }

    // ✅ Update an existing item
    @PutMapping("/update")
    public ApiResponse<String> update(@RequestBody ProjectBudgetItemDTO item) {
        boolean success = projectBudgetItemDAO.update(item);
        if (success) {
            return ApiResponse.success("Item updated successfully", null);
        } else {
            return ApiResponse.fail("Failed to update item", "Database update failed");
        }
    }

    // ✅ Delete by ID
    @DeleteMapping("/delete")
    public ApiResponse<String> delete(@RequestParam Long id) {
        boolean success = projectBudgetItemDAO.deleteById(id);
        if (success) {
            return ApiResponse.success("Item deleted successfully", null);
        } else {
            return ApiResponse.fail("Failed to delete item", "Database delete failed");
        }
    }
}