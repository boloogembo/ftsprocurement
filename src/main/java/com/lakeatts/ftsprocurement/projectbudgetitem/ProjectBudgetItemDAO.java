package com.lakeatts.ftsprocurement.projectbudgetitem;
 

import java.util.List;

public interface ProjectBudgetItemDAO {

    boolean add(ProjectBudgetItemDTO item);

    boolean update(ProjectBudgetItemDTO item);

    boolean deleteById(Long id);

    ProjectBudgetItemDTO getById(Long id);

    List<ProjectBudgetItemDTO> getByProjectId(Long projectId);

    List<ProjectBudgetItemDTO> getAll();
}