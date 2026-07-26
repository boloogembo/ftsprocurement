package com.lakeatts.ftsprocurement.approvalvotes;

import java.util.List;

public interface ApprovalVoteDAO {

    int insert(ApprovalVoteDTO dto);

    List<ApprovalVoteDTO> getByRequestAndStep(Long requestId, Long stepId);

    Double getTotalWeight(Long requestId, Long stepId, String vote);
}