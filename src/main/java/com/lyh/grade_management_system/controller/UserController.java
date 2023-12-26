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
    @Autowired
    UserService userService;

    @RequestMapping("/user/index")
    public String selectList(Model model, ModelMap map) {
        List<User> list = userService.selectList();
        model.addAttribute("users", list);
        return "/user/index";
    }

    @RequestMapping("/login")
    public String login(Model model, ModelMap map) {
        return "/login";
    }

    @RequestMapping("/register")
    public String register(Model model, ModelMap map) {
        return "/register";
    }
    // <form action="/user/login" method="post">
    //     <label>
    //         <input type="text" name="username" placeholder="用户名">
    //     </label>
    //     <label>
    //         <input type="password" name="password" placeholder="密码">
    //     </label>
    //     <input type="submit" value="登陆">
    // </form>

    @RequestMapping("/user/login")
    public String loginAction(String username, String password, Model model, ModelMap map) {
        // 查询数据库
        User user = userService.selectUserByName(username);
        // 判断用户名和密码是否正确
        if (user == null) {
            model.addAttribute("msg", "用户名不存在");
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("msg", "密码错误");
        }
        return "/login_result";
    }
}
