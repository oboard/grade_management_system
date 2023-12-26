package com.lyh.grade_management_system.bean;
import lombok.Data;
@Data
public class User {
    private Long id;
    // 1 老师 0 学生
    private Integer type;
    private String name;
    private String password;
}