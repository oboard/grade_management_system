package com.lyh.grade_management_system.service.impl;

import com.lyh.grade_management_system.bean.Clazz;
import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.mapper.ClazzMapper;
import com.lyh.grade_management_system.service.ClazzService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    final ClazzMapper clazzMapper;

    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public List<Clazz> selectList(String name) {
        return clazzMapper.selectList(name);
    }

    @Override
    public Clazz selectById(Long id) {
        return clazzMapper.selectById(id);
    }

    @Override
    public List<Clazz> selectListByUserId(List<User> userList) {
        return clazzMapper.selectByUserIds(userList);
    }

    @Override
    public Long replace(Clazz clazz) {
        return clazzMapper.replace(clazz);
    }

    @Override
    public void delete(Long id) {
        clazzMapper.delete(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
}
