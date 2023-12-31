package com.lyh.grade_management_system.controller;

import com.lyh.grade_management_system.bean.User;
import com.lyh.grade_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/index")
    public String selectList(Model model) {
        List<User> list = userService.selectList();
        model.addAttribute("users", list);
        return "/user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    @RequestMapping("/user/login")
    public String loginAction(String username, String password, Model model) {
        // 查询数据库
        User user = userService.selectByUsername(username);
        // 判断用户名和密码是否正确
        if (user == null) {
            model.addAttribute("msg", "用户名不存在");
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("msg", "密码错误");
        } else {
            return "redirect:/student/index";
        }
        return "/login_result";
    }

    @RequestMapping("/user/register")
    public String registerAction(String username, String password, Model model) {
        // 查询数据库
        User user = userService.selectByUsername(username);
        // 判断用户名是否存在
        if (user != null) {
            model.addAttribute("msg", "用户名已存在");
        } else {
            // 插入数据库
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.insert(user);
            model.addAttribute("msg", "注册成功");
        }
        return "/register_result";
    }
}
