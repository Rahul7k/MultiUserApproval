package com.xalts.multiUserApproval.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TaskRequestVO {
    private String description;
    private String creatorId;
    private List<String> approverIds;
}
