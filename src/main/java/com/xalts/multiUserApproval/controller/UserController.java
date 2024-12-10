package com.xalts.multiUserApproval.controller;

import com.xalts.multiUserApproval.service.UserService;
import com.xalts.multiUserApproval.vo.LoginRequestVO;
import com.xalts.multiUserApproval.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserVO user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestVO request) {
        return ResponseEntity.ok(userService.authenticateUser(request));
    }
}
