package com.lakeatts.ftsprocurement.approvalrequests;
 

import org.springframework.web.bind.annotation.*;

import com.lakeatts.ftsprocurement.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/approval-requests")
public class ApprovalRequestController {

    private final ApprovalRequestDAO dao;

    public ApprovalRequestController(ApprovalRequestDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> create(@RequestBody ApprovalRequestDTO dto) {
        try {
            return ApiResponse.success("Request created successfully", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Failed to create request", e.getMessage());
        }
    }

    @PutMapping("/status")
    public ApiResponse<Integer> updateStatus(
            @RequestParam Long id,
            @RequestParam String status
    ) {
        try {
            return ApiResponse.success("Status updated", dao.updateStatus(id, status));
        } catch (Exception e) {
            return ApiResponse.fail("Update failed", e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<List<ApprovalRequestDTO>> getAll() {
        try {
            return ApiResponse.success("Fetched successfully", dao.getAll());
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }

    @GetMapping("/by-organisation")
    public ApiResponse<List<ApprovalRequestDTO>> getByOrganisation(
            @RequestParam Long organisationId
    ) {
        try {
            return ApiResponse.success("Fetched successfully", dao.getByOrganisation(organisationId));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }
}