package com.lyh.grade_management_system.mapper;

import com.lyh.grade_management_system.bean.Clazz;
import com.lyh.grade_management_system.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClazzMapper {
    List<Clazz> selectList(String name);

    Long replace(Clazz clazz);

    void delete(Long id);

    void update(Clazz clazz);

    Clazz selectById(Long id);

    List<Clazz> selectByUserIds(@Param("userList") List<User> userList);
}
