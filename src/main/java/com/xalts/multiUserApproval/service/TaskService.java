package com.xalts.multiUserApproval.service;

import com.xalts.multiUserApproval.dao.entity.Task;
import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.dao.entity.UserApproval;
import com.xalts.multiUserApproval.dao.repo.TaskRepository;
import com.xalts.multiUserApproval.dao.repo.UserApprovalRepository;
import com.xalts.multiUserApproval.dao.repo.UserRepository;
import com.xalts.multiUserApproval.impl.MailSenderImpl;
import com.xalts.multiUserApproval.vo.req.ApprovalReq;
import com.xalts.multiUserApproval.vo.req.TaskReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserApprovalRepository approvalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderImpl mailSender;

    public String createTask(TaskReq request) {
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setStatus("PENDING");
        task.setApproverIds(request.getApproverIds());
        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("Creator not found"));
        task.setCreator(creator);
        taskRepository.save(task);
        String respMessage = "Task Created";
        if (!request.getUserEmails().isEmpty()){
            for (String email : request.getUserEmails()){
                String messageDesc = "A new task is created by " + request.getCreatorId();
                mailSender.sendEmail(email, "Task Created", messageDesc);
            }
            respMessage = respMessage + " And Mail Sent";
        }

        return respMessage;
    }

    public String approveTask(ApprovalReq request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        List<String> approverIds = new ArrayList<>();
        if (task.getApproverIds() != null) {
            approverIds = task.getApproverIds();
        }
        if(approverIds.isEmpty() || !approverIds.contains(request.getApprover().getUserId())){
            approverIds.add(request.getApprover().getUserId());
            task.setApproverIds(approverIds);
            UserApproval approval = new UserApproval();
            approval.setTask(task);
            approval.setApprover(request.getApprover());
            approval.setComment(request.getComment());
            approval.setApproved(true);
            approvalRepository.save(approval);
            updateTaskTable(task);

            int taskCount = approvalRepository.countByTaskId(task.getTid());
            if (taskCount >= 3) {
                updateTaskStatus(task);
            }
            return "Task Successfully Approved By : " + request.getApprover().getUserId();
        } else {
            return "Task is Already Approved by the user : " + request.getApprover().getUserId();
        }

    }

    public void updateTaskTable(Task task){
        taskRepository.save(task);
    }

    public void updateTaskStatus(Task task){
            task.setStatus("APPROVED");
            taskRepository.save(task);
    }
}
