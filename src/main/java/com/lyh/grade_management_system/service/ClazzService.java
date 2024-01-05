package com.lyh.grade_management_system.service;

import com.lyh.grade_management_system.bean.Clazz;
import com.lyh.grade_management_system.bean.Score;
import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface ClazzService {
    public Long replace(Clazz clazz);
    public void delete(Long id);
    public void update(Clazz clazz);

    List<Clazz> selectList(String name);

    Clazz selectById(Long id);

    List<Clazz> selectListByUserId(List<User> userList);
}