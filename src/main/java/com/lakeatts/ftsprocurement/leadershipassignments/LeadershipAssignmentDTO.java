package com.lakeatts.ftsprocurement.leadershipassignments;

import lombok.Data;

@Data
public class LeadershipAssignmentDTO {

    private Long id;
    private String designation;
    private Integer denominationId;
    private Long personnelId;
    private Long organisationId;
    private Boolean active;

 }