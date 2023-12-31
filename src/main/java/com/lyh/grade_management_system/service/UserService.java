package com.lyh.grade_management_system.service;

import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface UserService {
    public User selectByUsername(String username);
    public Long insert(User user);
    public void delete(Long id);
    public void update(User user);

    List<User> selectList();

    User selectById(Long id);
}