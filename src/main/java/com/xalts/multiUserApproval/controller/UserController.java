package com.xalts.multiUserApproval.controller;

import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.service.UserService;
import com.xalts.multiUserApproval.vo.LoginRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestVO request) {
        return ResponseEntity.ok(userService.authenticateUser(request));
    }
}
