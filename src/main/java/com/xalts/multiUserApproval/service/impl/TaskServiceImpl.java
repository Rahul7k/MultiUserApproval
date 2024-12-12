package com.xalts.multiUserApproval.service.impl;

import com.xalts.multiUserApproval.impl.TaskApprovalEvent;
import com.xalts.multiUserApproval.dao.entity.Task;
import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.dao.entity.UserApproval;
import com.xalts.multiUserApproval.dao.repo.TaskRepository;
import com.xalts.multiUserApproval.dao.repo.UserApprovalRepository;
import com.xalts.multiUserApproval.dao.repo.UserRepository;
import com.xalts.multiUserApproval.impl.MailSenderImpl;
import com.xalts.multiUserApproval.service.TaskService;
import com.xalts.multiUserApproval.vo.req.ApprovalReq;
import com.xalts.multiUserApproval.vo.req.TaskReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserApprovalRepository approvalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderImpl mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public String createTask(TaskReq request) {
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setStatus("PENDING");
        task.setApproverEmails(request.getApproverEmails());
        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("Creator not found"));
        task.setCreator(creator);
        taskRepository.save(task);
        String respMessage = "Task Created";
        if (!request.getApproverEmails().isEmpty()){
            for (String email : request.getApproverEmails()){
                String messageDesc = "A new task is created by " + request.getCreatorId();
                mailSender.sendEmail(email, "Task Created", messageDesc);
            }
            respMessage = respMessage + " And Mail Sent";
        }

        return respMessage;
    }

    @Override
    public String approveTask(ApprovalReq request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        UserApproval approval = new UserApproval();
        approval.setTask(task);
        approval.setApproverEmail(request.getApprover().getEmail());
        approval.setComment(request.getComment());
        approval.setApproved(true);
        approvalRepository.save(approval);
        eventPublisher.publishEvent(new TaskApprovalEvent(this, request.getApprover().getEmail()));

        int taskCount = approvalRepository.countByTaskId(task.getTid());
        if (taskCount >= 3) {
            updateTaskStatus(task);
        }
        return "Task Successfully Approved By : " + request.getApprover().getEmail();

    }

    @Override
    public void updateTaskStatus(Task task){
            task.setStatus("APPROVED");
            taskRepository.save(task);
            List<String> approverEmails = task.getApproverEmails();
            if (!Objects.isNull(approverEmails)){
                for (String email : approverEmails){
                    String messageDesc = "The task is successfully approved";
                    mailSender.sendEmail(email, "Task APPROVED", messageDesc);
                }
            }
    }
}
