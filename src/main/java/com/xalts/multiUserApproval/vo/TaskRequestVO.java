package com.xalts.multiUserApproval.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TaskRequestVO {
    private String description;
    private Long creatorId;
    private List<Long> approverIds;
}
