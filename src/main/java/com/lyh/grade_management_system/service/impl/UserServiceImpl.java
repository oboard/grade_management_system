package com.lyh.grade_management_system.service.impl;

import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.mapper.UserMapper;
import com.lyh.grade_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public List<User> selectList() {
        return userMapper.selectList();
    }
}
