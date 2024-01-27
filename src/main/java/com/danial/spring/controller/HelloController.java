package com.danial.spring.controller;


import com.danial.spring.model.User;
import com.danial.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class HelloController {
    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String indexView(Model model) {
        List<User> listUser = userService.getALL();
        model.addAttribute("users", listUser);
        return "index";
    }

    @RequestMapping("/addUser")
    public String addUserView(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUserField";
    }

    @RequestMapping("/addOrUpdate")
    public String add(@ModelAttribute("user") User user) {
        userService.addOrUpdate(user);
        return "redirect:/";
    }

    @RequestMapping("/removeUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id, Model model) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "addUserField";
    }
}
