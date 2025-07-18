package com.example.board.service;

import com.example.board.dto.User;
import com.example.board.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public User register(User user) {
        // 중복 체크
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("이미 존재하는 사용자명입니다.");
        }
        
        userMapper.insertUser(user);
        return userMapper.findByUsername(user.getUsername());
    }
    
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }
    
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return userMapper.findById(user.getId());
    }
}
