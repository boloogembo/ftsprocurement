package com.lakeatts.ftsprocurement.leadershipassignments;

import org.springframework.web.bind.annotation.*;

import com.lakeatts.ftsprocurement.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/leadership-assignments")
public class LeadershipAssignmentController {

    private final LeadershipAssignmentDAO dao;

    public LeadershipAssignmentController(LeadershipAssignmentDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> create(@RequestBody LeadershipAssignmentDTO dto) {
        try {
            return ApiResponse.success("Assignment created", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Creation failed", e.getMessage());
        }
    }

    @PutMapping
    public ApiResponse<Integer> update(@RequestBody LeadershipAssignmentDTO dto) {
        try {
            return ApiResponse.success("Assignment updated", dao.update(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Update failed", e.getMessage());
        }
    }

    @PutMapping("/deactivate")
    public ApiResponse<Integer> deactivate(@RequestParam Long id) {
        try {
            return ApiResponse.success("Assignment deactivated", dao.deactivate(id));
        } catch (Exception e) {
            return ApiResponse.fail("Deactivation failed", e.getMessage());
        }
    }

    @DeleteMapping
    public ApiResponse<Integer> delete(@RequestParam Long id) {
        try {
            return ApiResponse.success("Assignment deleted", dao.delete(id));
        } catch (Exception e) {
            return ApiResponse.fail("Delete failed", e.getMessage());
        }
    }

    @GetMapping("/by-organisation")
    public ApiResponse<List<LeadershipAssignmentDTO>> getByOrganisation(
            @RequestParam Long organisationId
    ) {
        try {
            return ApiResponse.success("Fetched",
                    dao.getByOrganisation(organisationId));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }

    @GetMapping("/by-designation")
    public ApiResponse<List<LeadershipAssignmentDTO>> getByDesignation(
            @RequestParam String designation,
            @RequestParam Integer denominationId
    ) {
        try {
            return ApiResponse.success("Fetched",
                    dao.getByDesignation(designation, denominationId));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }
}