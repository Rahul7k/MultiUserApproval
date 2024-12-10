package com.xalts.multiUserApproval.vo.req;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TaskReq {
    private String description;
    private String creatorId;
    private List<String> approverIds;
    private List<String> userEmails;
}
