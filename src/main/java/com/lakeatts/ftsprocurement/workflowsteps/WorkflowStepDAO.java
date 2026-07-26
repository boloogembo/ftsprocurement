package com.lakeatts.ftsprocurement.workflowsteps;

import java.util.List;

public interface WorkflowStepDAO {

    int insert(WorkflowStepDTO dto);

    int update(WorkflowStepDTO dto);

    int delete(Long id);

    WorkflowStepDTO getById(Long id);

    List<WorkflowStepDTO> getByWorkflowId(Long workflowId);

    List<WorkflowStepDTO> getAll();
}
