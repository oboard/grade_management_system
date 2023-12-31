package com.lyh.grade_management_system.service.impl;

import com.lyh.grade_management_system.bean.Subject;
import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.mapper.SubjectMapper;
import com.lyh.grade_management_system.service.SubjectService;
import com.lyh.grade_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public Subject selectByName(String name) {
        return subjectMapper.selectByName(name);
    }

    @Override
    public List<Subject> selectList() {
        return subjectMapper.selectList();
    }

    @Override
    public Subject selectById(Long id) {
        return subjectMapper.selectById(id);
    }

    @Override
    public Long insert(Subject subject) {
        return subjectMapper.insert(subject);
    }

    @Override
    public void delete(Long id) {
        subjectMapper.delete(id);
    }

    @Override
    public void update(Subject subject) {
        subjectMapper.update(subject);
    }
}
