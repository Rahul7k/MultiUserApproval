package com.xalts.multiUserApproval.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter @Setter
public class TaskApprovalEvent extends ApplicationEvent {

    private final String approverEmail;

    public TaskApprovalEvent(Object source, String approverEmail) {
        super(source);
        this.approverEmail = approverEmail;
    }

}
