package com.lyh.grade_management_system.service.impl;

import com.lyh.grade_management_system.bean.Score;
import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.mapper.ScoreMapper;
import com.lyh.grade_management_system.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    final
    ScoreMapper scoreMapper;

    public ScoreServiceImpl(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public List<Score> selectList() {
        return scoreMapper.selectList();
    }

    @Override
    public Score selectById(Long id) {
        return scoreMapper.selectById(id);
    }

    @Override
    public List<Score> selectListByUserId(List<User> userList) {
        return scoreMapper.selectByUserIds(userList);
    }

    @Override
    public Long replace(Score score) {
        return scoreMapper.replace(score);
    }

    @Override
    public void delete(Long id) {
        scoreMapper.delete(id);
    }

    @Override
    public void update(Score score) {
        scoreMapper.update(score);
    }
}
