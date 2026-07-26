package com.lakeatts.ftsprocurement.approvalstepinstances;
 

import java.util.List;

public interface ApprovalStepInstanceDAO {

    int insert(ApprovalStepInstanceDTO dto);

    int updateStatus(Long id, String status);

    List<ApprovalStepInstanceDTO> getByRequestId(Long requestId);
}
