package com.lyh.grade_management_system.mapper;

import com.lyh.grade_management_system.bean.Score;
import com.lyh.grade_management_system.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScoreMapper {
    List<Score> selectList();

    Long replace(Score score);

    void delete(Long id);

    void update(Score score);

    Score selectById(Long id);

    List<Score> selectByUserIds(@Param("userList") List<User> userList);
}
