package com.lyh.grade_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lyh.grade_management_system.mapper")
public class GradeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradeManagementSystemApplication.class, args);
    }

}
