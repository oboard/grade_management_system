package com.lyh.grade_management_system.controller;

import com.lyh.grade_management_system.bean.*;
import com.lyh.grade_management_system.service.ClazzService;
import com.lyh.grade_management_system.service.ScoreService;
import com.lyh.grade_management_system.service.SubjectService;
import com.lyh.grade_management_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    final
    ClazzService clazzService;
    final
    ScoreService scoreService;
    final UserService userService;
    final SubjectService subjectService;

    public ClazzController(ClazzService clazzService, ScoreService scoreService, UserService userService, SubjectService subjectService) {
        this.clazzService = clazzService;
        this.scoreService = scoreService;
        this.userService = userService;
        this.subjectService = subjectService;
    }

    // http://localhost:8080/templates/clazz/index?gradename=lyh&grade=&major=&clazz=
    // 筛选方式
    @RequestMapping("/index")
    public String selectList(
            String name,
            Model model
    ) {
        List<Clazz> clazzs = clazzService.selectList(name);

        model.addAttribute("name", name);
        model.addAttribute("clazzs", clazzs);
        return "/clazz/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "/clazz/add";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        clazzService.delete(id);
        // 重定向到index
        return "redirect:/clazz/index";
    }

    @RequestMapping("/edit")
    public String edit(Long id, Model model) {
        Clazz clazz = clazzService.selectById(id);
        model.addAttribute("clazz", clazz);
        return "/clazz/edit";
    }

    @RequestMapping("/edit_action")
    public String editAction(
            Long id,
            String name
    ) {
        Clazz clazzItem = new Clazz();
        clazzItem.setId(id);
        clazzItem.setName(name);
        clazzService.update(clazzItem);
        // 重定向到index
        return "redirect:/clazz/index";
    }

    @RequestMapping("/add_action")
    public String addAction(
            String name
    ) {
        Clazz clazzItem = new Clazz();
        clazzItem.setName(name);
        clazzService.replace(clazzItem);
        // 重定向到index
        return "redirect:/clazz/index";
    }


    @RequestMapping("/mark")
    public String mark(
            Long clazzId,
            Model model
    ) {
        List<User> userList = userService.selectListByClazzId(clazzId);
        if (!userList.isEmpty()) {
            List<Score> scoreList = scoreService.selectListByUserId(userList);
            if (!scoreList.isEmpty()) {
                List<Subject> subjectList = subjectService.selectListBySubjectId(scoreList);

                List<StudentScore> studentClazzList = new LinkedList<>();
                for (int i = 0; i < scoreList.size(); i++) {
                    var studentClazz = new StudentScore();
                    studentClazz.setScore(scoreList.get(i));
                    studentClazz.setStudent(userList.get(i));
                    studentClazz.setSubject(subjectList.get(i));
                    studentClazzList.add(studentClazz);
                }

                model.addAttribute("list", studentClazzList);
            }
        }
        return "/clazz/mark";
    }
}
