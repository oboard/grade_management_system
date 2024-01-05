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

    // http://localhost:8080/templates/score/index?gradename=lyh&grade=&major=&clazz=
    // 筛选方式
    @RequestMapping("/index")
    public String selectList(
            String username,
            Integer grade,
            String major,
            Model model
    ) {
        List<User> userList = userService.selectList();
        // 过滤role为0的用户
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRole() != 0) {
                userList.remove(i);
                i--;
            }
        }

        // 筛选
        if (username != null && !username.isEmpty()) {
            for (int i = 0; i < userList.size(); i++) {
                if (!userList.get(i).getUsername().equals(username)) {
                    userList.remove(i);
                    i--;
                }
            }
        }
        if (grade != null) {
            for (int i = 0; i < userList.size(); i++) {
                if (!userList.get(i).getGrade().equals(grade)) {
                    userList.remove(i);
                    i--;
                }
            }
        }
        if (major != null && !major.isEmpty()) {
            for (int i = 0; i < userList.size(); i++) {
                if (!userList.get(i).getMajor().equals(major)) {
                    userList.remove(i);
                    i--;
                }
            }
        }

        if (!userList.isEmpty()) {
            List<Score> scoreList = scoreService.selectListByUserId(userList);
            if (!scoreList.isEmpty()) {
                List<Subject> subjectList = subjectService.selectListBySubjectId(scoreList);

                List<StudentScore> studentScoreList = new LinkedList<>();
                for (int i = 0; i < scoreList.size(); i++) {
                    var studentScore = new StudentScore();
                    studentScore.setScore(scoreList.get(i));
                    studentScore.setStudent(userList.get(i));
                    studentScore.setSubject(subjectList.get(i));
                    studentScoreList.add(studentScore);
                }

                model.addAttribute("list", studentScoreList);
            }
        }
        return "/score/index";
    }

//    @RequestMapping("/add")
//    public String add() {
//        return "/score/add";
//    }
//
//    @RequestMapping("/delete")
//    public String delete(Long id) {
//        scoreService.delete(id);
//        // 重定向到index
//        return "redirect:/score/index";
//    }

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
        // 重定向到index
        return "redirect:/score/index";
    }

    @RequestMapping("/add_action")
    public String addAction(
            Long student_id,
            Long subject_id,
            Long score
    ) {
        Score scoreItem = new Score();
        scoreItem.setStudentId(student_id);
        scoreItem.setSubjectId(subject_id);
        scoreItem.setScore(score);
        scoreService.replace(scoreItem);
        // 重定向到index
        return "redirect:/score/index";
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
