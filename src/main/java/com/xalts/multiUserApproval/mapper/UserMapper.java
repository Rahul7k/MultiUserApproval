package com.xalts.multiUserApproval.mapper;

import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserMapper {

    User userVoToUser(UserVO userVO);
}
