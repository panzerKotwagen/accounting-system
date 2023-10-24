package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int userId) {
        return userService.get(userId);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int userId) {
        userService.delete(userId);
        return "User with ID = " + userId + " was deleted.";
    }
}
