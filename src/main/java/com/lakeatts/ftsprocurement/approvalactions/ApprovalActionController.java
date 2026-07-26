
package com.lakeatts.ftsprocurement.approvalactions; 
import org.springframework.web.bind.annotation.*;

import com.lakeatts.ftsprocurement.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/approval-actions")
public class ApprovalActionController {

    private final ApprovalActionDAO dao;

    public ApprovalActionController(ApprovalActionDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public ApiResponse<Integer> create(@RequestBody ApprovalActionDTO dto) {
        try {
            return ApiResponse.success("Created", dao.insert(dto));
        } catch (Exception e) {
            return ApiResponse.fail("Error", e.getMessage());
        }
    }

    @GetMapping("/by-request")
    public ApiResponse<List<ApprovalActionDTO>> getByRequest(@RequestParam Long requestId) {
        try {
            return ApiResponse.success("Success", dao.getByRequestId(requestId));
        } catch (Exception e) {
            return ApiResponse.fail("Error", e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<List<ApprovalActionDTO>> getAll() {
        try {
            return ApiResponse.success("Success", dao.getAll());
        } catch (Exception e) {
            return ApiResponse.fail("Error", e.getMessage());
        }
    }
}
