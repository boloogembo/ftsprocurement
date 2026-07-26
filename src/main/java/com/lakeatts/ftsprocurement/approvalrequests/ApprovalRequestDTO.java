package com.lakeatts.ftsprocurement.approvalrequests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class ApprovalRequestDTO {

    private Long id;
    private Long workflowId;
    private String referenceNumber;
    private Long organisationId;
    private BigDecimal amount;
    private String status;
    private Long currentStepId;
    private Long createdBy;
    private LocalDateTime createdAt;
    private String departmentName;
    private String projectName;
    private LocalDateTime approvalDeadline;

    // Getters & Setters
}