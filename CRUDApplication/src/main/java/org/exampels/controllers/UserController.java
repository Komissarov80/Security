package org.exampels.controllers;

import org.exampels.Services.UserServices;
import org.exampels.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class UserController {
    @Autowired
    private UserServices userServices;

    // создать юзера
    @GetMapping("/user-create")
    public String addUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String addUser(User user) {
        userServices.addUser(user);
        return "redirect:/users";
    }


    // Получить всех юзеров
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> usersList = userServices.getAllUsers();
        model.addAttribute("users", usersList);
        return "user-list";
    }

    // Получить одного юзера по ид номеру и передать в представление
    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        System.out.println("in controller");
        User user = userServices.getUser(id);
        System.out.println("in controller user is "+user);
        model.addAttribute("user", user);
        return "user";
    }

    // удалить юзера из главной таблицы
    @PostMapping("/delete/{id}")
    public String deletUser(@PathVariable("id") Long id) {
        userServices.deletUser(id);
        return "redirect:/users";
    }
    // удалить юзера из url строки
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServices.deletUser(id);
        return "redirect:/users";
    }

    // изменить
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
