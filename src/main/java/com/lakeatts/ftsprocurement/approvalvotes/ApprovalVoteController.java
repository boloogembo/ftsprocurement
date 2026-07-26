package com.lakeatts.ftsprocurement.approvalvotes;

import org.springframework.web.bind.annotation.*;

import com.lakeatts.ftsprocurement.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/approval-votes")
public class ApprovalVoteController {

    private final ApprovalVoteDAO dao;

    public ApprovalVoteController(ApprovalVoteDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> vote(@RequestBody ApprovalVoteDTO dto) {
        try {
            return ApiResponse.success("Vote submitted", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Vote failed", e.getMessage());
        }
    }

    @GetMapping("/by-step")
    public ApiResponse<List<ApprovalVoteDTO>> getVotes(
            @RequestParam Long requestId,
            @RequestParam Long stepId
    ) {
        try {
            return ApiResponse.success("Fetched votes",
                    dao.getByRequestAndStep(requestId, stepId));
        } catch (Exception e) {
            return ApiResponse.fail("Fetch failed", e.getMessage());
        }
    }

    @GetMapping("/weight")
    public ApiResponse<Double> getWeight(
            @RequestParam Long requestId,
            @RequestParam Long stepId,
            @RequestParam String vote
    ) {
        try {
            return ApiResponse.success("Total weight",
                    dao.getTotalWeight(requestId, stepId, vote));
        } catch (Exception e) {
            return ApiResponse.fail("Error", e.getMessage());
        }
    }
}