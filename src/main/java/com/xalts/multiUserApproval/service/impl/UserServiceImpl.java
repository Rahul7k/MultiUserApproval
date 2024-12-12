package com.xalts.multiUserApproval.service.impl;

import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.dao.repo.UserRepository;
import com.xalts.multiUserApproval.mapper.UserMapper;
import com.xalts.multiUserApproval.service.UserService;
import com.xalts.multiUserApproval.vo.req.LoginReq;
import com.xalts.multiUserApproval.vo.req.UserReq;
import com.xalts.multiUserApproval.vo.resp.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String registerUser(UserReq userReq) {
        User user = userMapper.userReqToUser(userReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully with ID: " + user.getUserId();
    }

    @Override
    public String authenticateUser(LoginReq request) {
        User user = userRepository.findById(request.getLoginId())
                .orElseThrow(() -> new RuntimeException("Invalid login ID"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Login successful";
        }
        return "Invalid credentials";
    }

    @Override
    public List<UserResp> getAllUsers(){
        Iterable<User> allUsers = userRepository.findAll();
        List<UserResp> allUserResp = new ArrayList<>();
        for (User user : allUsers){
            UserResp userResp = userMapper.userToUserResp(user);
            allUserResp.add(userResp);
        }
        return allUserResp;
    }
}
