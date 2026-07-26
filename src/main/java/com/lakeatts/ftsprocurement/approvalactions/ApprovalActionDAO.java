
package com.lakeatts.ftsprocurement.approvalactions;

import java.util.List;

public interface ApprovalActionDAO {

    int insert(ApprovalActionDTO dto);

    List<ApprovalActionDTO> getByRequestId(Long requestId);

    List<ApprovalActionDTO> getAll();
}
