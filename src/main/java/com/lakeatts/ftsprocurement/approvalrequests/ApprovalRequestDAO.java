package com.lakeatts.ftsprocurement.approvalrequests;

import java.util.List;

public interface ApprovalRequestDAO {

    int insert(ApprovalRequestDTO dto);

    int updateStatus(Long id, String status);

    List<ApprovalRequestDTO> getAll();

    List<ApprovalRequestDTO> getByOrganisation(Long organisationId);
}