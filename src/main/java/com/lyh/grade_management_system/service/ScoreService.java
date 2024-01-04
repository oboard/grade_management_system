package com.lyh.grade_management_system.service;

import com.lyh.grade_management_system.bean.Score;
import com.lyh.grade_management_system.bean.User;

import java.util.List;

public interface ScoreService {
    public Long replace(Score user);
    public void delete(Long id);
    public void update(Score user);

    List<Score> selectList();

    Score selectById(Long id);

    List<Score> selectListByUserId(List<User> userList);
}