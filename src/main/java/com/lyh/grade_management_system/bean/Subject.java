package com.lyh.grade_management_system.bean;
import lombok.Data;

@Data
public class Subject {

    private Long id;
    // 1 老师 0 学生
    private String name;
}