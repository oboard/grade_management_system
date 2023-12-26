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
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public List<User> selectList() {
        return userMapper.selectList();
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public Long insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
