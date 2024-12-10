package com.xalts.multiUserApproval.vo.req;

import com.xalts.multiUserApproval.dao.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApprovalReq {

    private Long taskId;
    private User approver;
    private String comment;

}
