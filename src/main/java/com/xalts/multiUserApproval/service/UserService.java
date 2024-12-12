package com.xalts.multiUserApproval.service;

import com.xalts.multiUserApproval.vo.req.LoginReq;
import com.xalts.multiUserApproval.vo.req.UserReq;
import com.xalts.multiUserApproval.vo.resp.UserResp;

import java.util.List;

public interface UserService {

    public String registerUser(UserReq userReq);

    public String authenticateUser(LoginReq request);

    public List<UserResp> getAllUsers();

}
