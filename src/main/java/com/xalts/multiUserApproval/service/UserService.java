package com.xalts.multiUserApproval.service;

import com.xalts.multiUserApproval.dao.entity.User;
import com.xalts.multiUserApproval.dao.repo.UserRepository;
import com.xalts.multiUserApproval.vo.LoginRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully with ID: " + user.getUid();
    }

    public String authenticateUser(LoginRequestVO request) {
        User user = new User();
        if (Objects.nonNull(request.getLoginId())){
            user = userRepository.findByLoginId(request.getLoginId());
        }

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}
