package com.lakeatts.ftsprocurement.approvalstepinstances;

import org.springframework.web.bind.annotation.*;

import com.lakeatts.ftsprocurement.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/approval-step-instances")
public class ApprovalStepInstanceController {

    private final ApprovalStepInstanceDAO dao;

    public ApprovalStepInstanceController(ApprovalStepInstanceDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> create(@RequestBody ApprovalStepInstanceDTO dto) {
        try {
            return ApiResponse.success("Step instance created", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Creation failed", e.getMessage());
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

    @GetMapping("/by-request")
    public ApiResponse<List<ApprovalStepInstanceDTO>> getByRequest(
            @RequestParam Long requestId
    ) {
        try {
            return ApiResponse.success("Fetched successfully", dao.getByRequestId(requestId));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }
}