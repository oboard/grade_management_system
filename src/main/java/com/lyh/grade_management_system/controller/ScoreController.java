package com.lyh.grade_management_system.controller;

import com.lyh.grade_management_system.bean.Score;
import com.lyh.grade_management_system.bean.StudentScore;
import com.lyh.grade_management_system.bean.Subject;
import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.service.ScoreService;
import com.lyh.grade_management_system.service.SubjectService;
import com.lyh.grade_management_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/score")
public class ScoreController {

    final
    ScoreService scoreService;
    final UserService userService;
    final SubjectService subjectService;

    public ScoreController(ScoreService scoreService, UserService userService, SubjectService subjectService) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.subjectService = subjectService;
    }

    @RequestMapping("/edit")
    public String edit(Long id, Model model) {
        Score score = scoreService.selectById(id);
        model.addAttribute("grade", score);
        return "/score/edit";
    }

    @RequestMapping("/edit_action")
    public String editAction(
            Long id,
            Long student_id,
            Long subject_id,
            Long score
    ) {
        Score scoreItem = new Score();
        scoreItem.setId(id);
        scoreItem.setStudentId(student_id);
        scoreItem.setSubjectId(subject_id);
        scoreItem.setScore(score);
        scoreService.update(scoreItem);
        return "/index";
    }


    @RequestMapping("/mark")
    public String mark(
            Long clazzId,
            Model model
    ) {
        List<User> userList = userService.selectListByClazzId(clazzId);
        List<StudentScore> studentScoreList = new ArrayList<>();
        List<Score> scoreList = scoreService.selectListByUserId(userList);
        List<Subject> subjectList = subjectService.selectList();
        for (Subject subject : subjectList) {
            for (User user : userList) {
                var studentScore = new StudentScore();
                studentScore.setStudent(user);
                // 从scoreList和subjectList中抽取
                studentScore.setSubject(subject);
                for (Score score : scoreList) {
                    if (Objects.equals(score.getStudentId(), user.getId())
                            && Objects.equals(score.getSubjectId(), subject.getId())) {
                        studentScore.setScore(score);
                        break;
                    }
                }
                if(studentScore.getScore() == null) {
                    Score score = new Score();
                    score.setScore(100L);
                    score.setStudentId(user.getId());
                    score.setSubjectId(subject.getId());
                    studentScore.setScore(score);
                }
                studentScoreList.add(studentScore);
            }
        }
        model.addAttribute("list", studentScoreList);
        return "/score/mark";
    }
}
