package com.xalts.multiUserApproval.service;

import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.dao.repo.UserRepository;
import com.xalts.multiUserApproval.mapper.UserMapper;
import com.xalts.multiUserApproval.vo.LoginRequestVO;
import com.xalts.multiUserApproval.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public String registerUser(UserVO userVo) {
        User user = userMapper.userVoToUser(userVo);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully with ID: " + user.getUserId();
    }

    public String authenticateUser(LoginRequestVO request) {
        User user = userRepository.findById(request.getLoginId())
                .orElseThrow(() -> new RuntimeException("Invalid login ID"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}
