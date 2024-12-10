package com.xalts.multiUserApproval.mapper;

import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.vo.req.UserReq;
import com.xalts.multiUserApproval.vo.resp.UserResp;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserMapper {

    User userReqToUser(UserReq userReq);

    UserResp userToUserResp(User user);
}
