package com.xalts.multiUserApproval.impl;

import org.springframework.context.event.EventListener;

public class TaskApprovalListener {

    @EventListener
    public void handleTaskApprovalEvent(TaskApprovalEvent event) {
        String approverEmail = event.getApproverEmail();

        System.out.println("Task has been approved by " + approverEmail);
    }
}
