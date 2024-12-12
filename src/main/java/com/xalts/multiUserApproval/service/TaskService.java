package com.xalts.multiUserApproval.service;

import com.xalts.multiUserApproval.dao.entity.Task;
import com.xalts.multiUserApproval.vo.req.ApprovalReq;
import com.xalts.multiUserApproval.vo.req.TaskReq;

public interface TaskService {

    public String createTask(TaskReq request);

    public String approveTask(ApprovalReq request);

    public void updateTaskStatus(Task task);

}
