package com.lyh.grade_management_system.service;

import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface UserService {
    public User selectUserByUsername(String username);
    public Long insertUser(User user);
    public void deleteUser(Long id);
    public User updateUser(User user);

    List<User> selectList();

    User selectUserById(Long id);
}