package com.xalts.multiUserApproval.controller;

import com.xalts.multiUserApproval.service.UserService;
import com.xalts.multiUserApproval.vo.req.LoginReq;
import com.xalts.multiUserApproval.vo.req.UserReq;
import com.xalts.multiUserApproval.vo.resp.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserReq user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReq request) {
        return ResponseEntity.ok(userService.authenticateUser(request));
    }

    @GetMapping("/getusers")
    public List<UserResp> getUsers() {
        List<UserResp> resp = userService.getAllUsers();
        return resp;
    }

}
