package com.lyh.grade_management_system.mapper;

import com.lyh.grade_management_system.bean.Score;
import com.lyh.grade_management_system.bean.Subject;

import java.util.List;

public interface SubjectMapper {
    Subject selectByName(String name);

    List<Subject> selectList();

    Long insert(Subject subject);

    void delete(Long id);

    void update(Subject subject);

    Subject selectById(Long id);

    List<Subject> selectBySubjectIds(List<Score> scoreList);
}
