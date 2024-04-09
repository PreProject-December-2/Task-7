package ru.itmentor.spring.boot_security.demo.restController;


import org.springframework.web.bind.annotation.*;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;


import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("api/v1/admin/")
public class RestAdminController {

    private final UserService userService;

    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.showUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping("/create")
    public User addNewUser(@RequestBody User user) {
        Set<Role> roles = user.getRoles();
        return this.userService.saveUser(user, roles);
    }

    @PutMapping("/create")
    public User updateUser(@RequestBody User user) {
        Set<Role> roles = user.getRoles();
        return this.userService.saveUser(user, roles);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User with id " + id + " delete";
    }

}
