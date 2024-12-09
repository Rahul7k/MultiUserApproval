package com.xalts.multiUserApproval.vo;

import com.xalts.multiUserApproval.dao.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApprovalRequestVO {

    private Long taskId;
    private User approver;
    private String comment;

}
