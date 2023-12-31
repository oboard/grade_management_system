package com.lyh.grade_management_system.service;

import com.lyh.grade_management_system.bean.Subject;

import java.util.List;

public interface SubjectService {
    public Subject selectByName(String name);
    public Long insert(Subject user);
    public void delete(Long id);
    public void update(Subject user);

    List<Subject> selectList();

    Subject selectById(Long id);
}