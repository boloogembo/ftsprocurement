package com.lakeatts.ftsprocurement.departmentprojects;

import java.util.List;

public interface DepartmentProjectDAO {

    int insert(DepartmentProjectDTO dto);

    int update(DepartmentProjectDTO dto);

    int delete(Long id);

    List<DepartmentProjectDTO> getByOrganisation(Integer organisationId);

    List<DepartmentProjectDTO> getByDepartment(Integer organisationId, String departmentName);
}