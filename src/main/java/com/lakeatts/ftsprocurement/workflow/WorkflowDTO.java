package com.lakeatts.ftsprocurement.workflow;
 

import java.time.LocalDateTime;

public class WorkflowDTO {

    private Long id;
    private String name;
    private Integer denominationId;
    private Integer organisationLevel;
    private Boolean active;
    private LocalDateTime createdAt;

    public WorkflowDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getDenominationId() { return denominationId; }
    public void setDenominationId(Integer denominationId) { this.denominationId = denominationId; }

    public Integer getOrganisationLevel() { return organisationLevel; }
    public void setOrganisationLevel(Integer organisationLevel) { this.organisationLevel = organisationLevel; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}