package com.lyh.grade_management_system.controller;

import com.lyh.grade_management_system.bean.Subject;
import com.lyh.grade_management_system.service.SubjectService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    final
    SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // http://localhost:8080/templates/subject/index?subjectname=lyh&grade=&major=&clazz=
    // 筛选方式
    @RequestMapping("/index")
    public String selectList(
            String name,
            Model model
    ) {
        List<Subject> list = subjectService.selectList();
        // 筛选
        if (name != null && !name.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getName().equals(name)) {
                    list.remove(i);
                    i--;
                }
            }
        }
        model.addAttribute("subjects", list);
        return "/subject/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "/subject/add";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        subjectService.delete(id);
        // 重定向到index
        return "redirect:/subject/index";
    }

    @RequestMapping("/edit")
    public String edit(Long id, Model model) {
        Subject subject = subjectService.selectById(id);
        model.addAttribute("subject", subject);
        return "/subject/edit";
    }

    @RequestMapping("/edit_action")
    public String editAction(
            Long id,
            String name
    ) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        subjectService.update(subject);
        // 重定向到index
        return "redirect:/subject/index";
    }

    @RequestMapping("/add_action")
    public String addAction(
            String name
    ) {
        Subject subject = new Subject();
        subject.setName(name);
        subjectService.insert(subject);
        // 重定向到index
        return "redirect:/subject/index";
    }
}
