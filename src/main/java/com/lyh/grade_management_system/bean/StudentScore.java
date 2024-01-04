package com.lyh.grade_management_system.bean;

import lombok.Data;

@Data
public class StudentScore {
    private Subject subject;
    private User student;
    private Score score;
}
