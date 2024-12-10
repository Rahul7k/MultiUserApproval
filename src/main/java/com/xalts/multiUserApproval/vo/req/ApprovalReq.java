package com.xalts.multiUserApproval.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xalts.multiUserApproval.dao.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApprovalReq {

    @JsonProperty("task_id")
    private Long taskId;

    @JsonProperty("approver")
    private UserReq approver;

    @JsonProperty("comment")
    private String comment;

}
