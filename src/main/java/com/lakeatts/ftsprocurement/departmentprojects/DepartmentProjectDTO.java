package com.lakeatts.ftsprocurement.departmentprojects;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DepartmentProjectDTO {

    private Long id;
    private Integer organisationId;
    private String departmentName;
    private String projectName;
    private String description;
    private Long createdBy;
    private LocalDateTime createdAt;

    
}