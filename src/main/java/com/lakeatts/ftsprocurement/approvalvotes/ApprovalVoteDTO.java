package com.lakeatts.ftsprocurement.approvalvotes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class ApprovalVoteDTO {

    private Long id;
    private Long requestId;
    private Long stepId;
    private Long voterId;
    private String designation;
    private Long organisationId;
    private String vote;
    private BigDecimal weight;
    private String comment;
    private LocalDateTime votedAt;
}