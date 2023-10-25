package ru.kotb.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kotb.accounting_system.entity.User;
import ru.kotb.accounting_system.exception_handling.NoSuchEntityException;
import ru.kotb.accounting_system.service.UserService;

import java.util.List;


/**
 * The Controller class that processes requests to /api/users.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * The UserService bean for working with the
     * users.
     */
    private final UserService userService;

    /**
     * Constructs the controller and links it with the service bean.
     *
     * @param userService the user service bean
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO: rewrite comment
    /**
     * Returns a JSON object with all users in the table.
     *
     * @return JSON object with all users in the table
     */
    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAll();
    }

    //TODO: rewrite comment
    /**
     * Returns a JSON object with description of the specified user.
     *
     * @param userId the specified ID of the user
     * @return the JSON object with the specified user
     */
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int userId) {
        User user = userService.get(userId);
        if (user == null) {
            throw new NoSuchEntityException("There is no user with ID = "
                    + userId + " in the database.");
        } else {
            return user;
        }
    }

    //TODO: rewrite comment
    /**
     * Accepts the JSON object with the description of the
     * user and saves it into the table.
     *
     * @param user JSON object with the description of the
     *                     user
     * @return accepted JSON object
     */
    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    /**
     * Updates the existing user in the table. The request
     * body must have got the JSON object with the ID of an existing
     * entity and the updated data.
     *
     * @param user entity object with specified ID
     * @return saved object
     */
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        if (userService.get(user.getId()) == null) {
            throw new NoSuchEntityException("There is no user with ID = "
                    + user.getId() + " in the database.");
        } else {
            userService.saveOrUpdate(user);
            return user;
        }
    }

    //TODO: rewrite comment
    /**
     * Deletes the user with the specified ID and return the
     * informative message.
     *
     * @param userId specified ID of the user
     * @return operation status message
     */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int userId) {
        User user = userService.get(userId);
        if (user == null) {
            throw new NoSuchEntityException("There is no user with ID = "
                    + userId + " in the database.");
        } else {
            userService.delete(userId);
            return "User with ID = " + userId + " was deleted.";
        }
    }
}
