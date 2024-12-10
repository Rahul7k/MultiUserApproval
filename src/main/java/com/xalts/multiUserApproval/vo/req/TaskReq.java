package com.xalts.multiUserApproval.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TaskReq {

    @JsonProperty("description")
    private String description;

    @JsonProperty("creator_id")
    private String creatorId;

    @JsonProperty("approver_emails")
    private List<String> approverEmails;
}
