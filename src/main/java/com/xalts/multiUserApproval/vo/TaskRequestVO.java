package com.xalts.multiUserApproval.vo;

import com.xalts.multiUserApproval.dao.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TaskRequestVO {
    private String description;
    private String creatorId;
    private List<String> approverIds;
    private List<String> userEmails;
}
