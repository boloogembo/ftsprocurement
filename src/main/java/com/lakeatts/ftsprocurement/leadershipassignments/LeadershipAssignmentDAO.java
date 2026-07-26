package com.lakeatts.ftsprocurement.leadershipassignments;

import java.util.List;

public interface LeadershipAssignmentDAO {

    int insert(LeadershipAssignmentDTO dto);

    int update(LeadershipAssignmentDTO dto);

    int deactivate(Long id);

    int delete(Long id);

    List<LeadershipAssignmentDTO> getByOrganisation(Long organisationId);

    List<LeadershipAssignmentDTO> getByDesignation(String designation, Integer denominationId);
}