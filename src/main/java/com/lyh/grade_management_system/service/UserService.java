package com.lyh.grade_management_system.service;

import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface UserService {
    public User selectUserByName(String username);

    List<User> selectList();
}