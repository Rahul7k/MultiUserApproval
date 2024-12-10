package com.xalts.multiUserApproval.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginReq {

    @JsonProperty("login_id")
    private String loginId;

    @JsonProperty("password")
    private String password;
}
