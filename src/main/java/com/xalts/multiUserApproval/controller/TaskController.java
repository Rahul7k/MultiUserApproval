package com.xalts.multiUserApproval.controller;

import com.xalts.multiUserApproval.service.TaskService;
import com.xalts.multiUserApproval.vo.ApprovalRequestVO;
import com.xalts.multiUserApproval.vo.TaskRequestVO;
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
    public ResponseEntity<String> createTask(@RequestBody TaskRequestVO request) {
        taskService.createTask(request);
        return ResponseEntity.ok("Task created successfully");
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approveTask(@RequestBody ApprovalRequestVO request) {
        taskService.approveTask(request);
        return ResponseEntity.ok("Task approved successfully");
    }

}
