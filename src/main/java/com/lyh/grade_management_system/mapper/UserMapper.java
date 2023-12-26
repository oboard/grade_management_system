package com.lyh.grade_management_system.mapper;

import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface UserMapper {
    User selectUserByName(String name);

    List<User> selectList();
}
