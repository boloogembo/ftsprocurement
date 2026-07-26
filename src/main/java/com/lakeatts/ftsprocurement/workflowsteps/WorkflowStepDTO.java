package com.lakeatts.ftsprocurement.workflowsteps;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WorkflowStepDTO {

    private Long id;
    private Long workflowId;
    private Integer stepOrder;
    private String stepName;
    private String approvalMode;
    private BigDecimal thresholdValue;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Boolean isFinalStep;
    private LocalDateTime createdAt;

    public WorkflowStepDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getWorkflowId() { return workflowId; }
    public void setWorkflowId(Long workflowId) { this.workflowId = workflowId; }

    public Integer getStepOrder() { return stepOrder; }
    public void setStepOrder(Integer stepOrder) { this.stepOrder = stepOrder; }

    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }

    public String getApprovalMode() { return approvalMode; }
    public void setApprovalMode(String approvalMode) { this.approvalMode = approvalMode; }

    public BigDecimal getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(BigDecimal thresholdValue) { this.thresholdValue = thresholdValue; }

    public BigDecimal getMinAmount() { return minAmount; }
    public void setMinAmount(BigDecimal minAmount) { this.minAmount = minAmount; }

    public BigDecimal getMaxAmount() { return maxAmount; }
    public void setMaxAmount(BigDecimal maxAmount) { this.maxAmount = maxAmount; }

    public Boolean getIsFinalStep() { return isFinalStep; }
    public void setIsFinalStep(Boolean isFinalStep) { this.isFinalStep = isFinalStep; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
