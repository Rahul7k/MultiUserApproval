package com.xalts.multiUserApproval.controller;

import com.xalts.multiUserApproval.service.TaskService;
import com.xalts.multiUserApproval.vo.req.ApprovalReq;
import com.xalts.multiUserApproval.vo.req.TaskReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskReq request) {
        String message = taskService.createTask(request);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approveTask(@RequestBody ApprovalReq request) {
        String message = taskService.approveTask(request);
        return ResponseEntity.ok(message);
    }

}
