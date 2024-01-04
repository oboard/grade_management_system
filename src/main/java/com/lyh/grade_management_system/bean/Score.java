package com.lyh.grade_management_system.bean;
import lombok.Data;

@Data
public class Score {

    private Long id;
    private Long studentId;
    private Long subjectId;
    private Long score;
}