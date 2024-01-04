package com.lyh.grade_management_system.mapper;

import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface UserMapper {
    User selectUserByUsername(String username);

    List<User> selectList();

    Long insertUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User selectUserById(Long id);

    List<User> selectListByClazzId(Long clazzId);
}
