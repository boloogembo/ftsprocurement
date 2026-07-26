package com.lakeatts.ftsprocurement.approvalstepinstances;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApprovalStepInstanceDTO {

    private Long id;
    private Long requestId;
    private Long stepId;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    
}