package com.lakeatts.ftsprocurement.departmentprojects;

import org.springframework.web.bind.annotation.*;

import com.lakeatts.ftsprocurement.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/department-projects")
public class DepartmentProjectController {

    private final DepartmentProjectDAO dao;

    public DepartmentProjectController(DepartmentProjectDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> create(@RequestBody DepartmentProjectDTO dto) {
        try {
            return ApiResponse.success("Project created", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Creation failed", e.getMessage());
        }
    }

    @PutMapping
    public ApiResponse<Integer> update(@RequestBody DepartmentProjectDTO dto) {
        try {
            return ApiResponse.success("Project updated", dao.update(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Update failed", e.getMessage());
        }
    }

    @DeleteMapping
    public ApiResponse<Integer> delete(@RequestParam Long id) {
        try {
            return ApiResponse.success("Project deleted", dao.delete(id));
        } catch (Exception e) {
            return ApiResponse.fail("Delete failed", e.getMessage());
        }
    }

    @GetMapping("/by-organisation")
    public ApiResponse<List<DepartmentProjectDTO>> getByOrganisation(
            @RequestParam Integer organisationId
    ) {
        try {
            return ApiResponse.success("Fetched",
                    dao.getByOrganisation(organisationId));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }

    @GetMapping("/by-department")
    public ApiResponse<List<DepartmentProjectDTO>> getByDepartment(
            @RequestParam Integer organisationId,
            @RequestParam String departmentName
    ) {
        try {
            return ApiResponse.success("Fetched",
                    dao.getByDepartment(organisationId, departmentName));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }
}