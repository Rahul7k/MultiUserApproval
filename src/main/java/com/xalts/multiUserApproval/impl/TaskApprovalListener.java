package com.xalts.multiUserApproval.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskApprovalListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleTaskApprovalEvent(TaskApprovalEvent event) {
        String approverEmail = "Notification: Task is Approved by " + event.getApproverEmail();

        System.out.println(approverEmail);
        messagingTemplate.convertAndSend("/topic/updates", approverEmail);
    }
}
