package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUser(Model model) {
        List<User> allUser = userService.showUsers();
        model.addAttribute("allUs", allUser);
        return "allUser";
    }

    @RequestMapping("/addNewUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("usId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("usId") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
@PostMapping("/updateRoles")
    public String roles(@RequestParam("usId") int id,@RequestParam("roles") String roles){
        userService.showUsersRoles(id,roles);
        return "redirect:/admin/users";
}
}
