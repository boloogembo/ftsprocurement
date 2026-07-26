package com.lakeatts.ftsprocurement.workflow;
 

import java.util.List;

public interface WorkflowDAO {

    int insert(WorkflowDTO dto);

    int update(WorkflowDTO dto);

    int delete(Long id);

    WorkflowDTO getById(Long id);

    List<WorkflowDTO> getAll();
}