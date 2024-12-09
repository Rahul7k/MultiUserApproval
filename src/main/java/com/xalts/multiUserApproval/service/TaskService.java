package com.xalts.multiUserApproval.service;

import com.xalts.multiUserApproval.dao.entity.Task;
import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.dao.entity.UserApproval;
import com.xalts.multiUserApproval.dao.repo.TaskRepository;
import com.xalts.multiUserApproval.dao.repo.UserApprovalRepository;
import com.xalts.multiUserApproval.dao.repo.UserRepository;
import com.xalts.multiUserApproval.vo.ApprovalRequestVO;
import com.xalts.multiUserApproval.vo.TaskRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserApprovalRepository approvalRepository;

    @Autowired
    private UserRepository userRepository;

    public void createTask(TaskRequestVO request) {
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setStatus("PENDING");
        task.setApproverIds(request.getApproverIds());
        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("Creator not found"));
        task.setCreator(creator);
        taskRepository.save(task);
    }

    public void approveTask(ApprovalRequestVO request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        UserApproval approval = new UserApproval();
        approval.setTask(task);
        approval.setApprover(request.getApprover());
        approval.setComment(request.getComment());
        approval.setApproved(true);
        approvalRepository.save(approval);

        if (approvalRepository.countByTaskId(task.getTid()) >= 3) {
            task.setStatus("APPROVED");
            taskRepository.save(task);
        }
    }
}
