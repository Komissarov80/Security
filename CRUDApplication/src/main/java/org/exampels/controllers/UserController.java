package org.exampels.controllers;

import org.exampels.services.UserServices;
import org.exampels.model.User;
import org.exampels.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/user-create")
    public String addUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String addUser(User user) {
        userServices.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> usersList = userServices.getAllUsers();
        model.addAttribute("users", usersList);
        return "user-list";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        User user = userServices.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/delete/{id}")
    public String deletUser(@PathVariable("id") Long id) {
        userServices.deletUser(id);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServices.deletUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userServices.getUser(id);
        model.addAttribute(user);
        return "user-update";
    }

    @PostMapping("user-update")
    public String updateUser(User user) {
        userServices.editUser(user);
        return "redirect:/users";
    }
}
