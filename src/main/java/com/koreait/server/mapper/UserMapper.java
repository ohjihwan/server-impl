package com.koreait.server.mapper;

import com.koreait.server.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User findById(Long id);
    void insertUser(User user);
    void updateUser(User user);
}
