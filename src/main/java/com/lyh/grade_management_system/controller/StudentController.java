package com.lyh.grade_management_system.controller;

import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    UserService userService;

    // http://localhost:8080/templates/student/index?username=lyh&grade=&major=&clazz=
    // 筛选方式
    @RequestMapping("/index")
    public String selectList(
            String username,
            Integer grade,
            String major,
            Model model,
            ModelMap map
    ) {
        List<User> list = userService.selectList();
        // 过滤role为0的用户
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole() != 0) {
                list.remove(i);
                i--;
            }
        }

        // 筛选
        if (username != null && !username.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getUsername().equals(username)) {
                    list.remove(i);
                    i--;
                }
            }
        }
        if (grade != null) {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getGrade().equals(grade)) {
                    list.remove(i);
                    i--;
                }
            }
        }
        if (major != null && !major.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getMajor().equals(major)) {
                    list.remove(i);
                    i--;
                }
            }
        }

        model.addAttribute("users", list);
        return "/student/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "/student/add";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        // 重定向到index
        return "redirect:/student/index";
    }

    @RequestMapping("/edit")
    public String edit(Long id, Model model) {
        User user = userService.selectById(id);
        model.addAttribute("student", user);
        return "/student/edit";
    }

    @RequestMapping("/edit_action")
    public String editAction(
            Long id,
            String username,
            Integer grade,
            String major,
            Long clazzId,
            Model model,
            ModelMap map
    ) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setGrade(grade);
        user.setMajor(major);
        user.setClazzId(clazzId);
        user.setRole(0);
        userService.update(user);
        // 重定向到index
        return "redirect:/student/index";
    }

    @RequestMapping("/add_action")
    public String addAction(
            String username,
            Integer grade,
            String major,
            Long clazzId,
            Model model,
            ModelMap map
    ) {
        User user = new User();
        user.setUsername(username);
        user.setGrade(grade);
        user.setMajor(major);
        user.setClazzId(clazzId);
        user.setRole(0);
        userService.insert(user);
        // 重定向到index
        return "redirect:/student/index";
    }
}
