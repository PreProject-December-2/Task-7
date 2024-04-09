package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {

        this.userService = userService;
        this.roleRepository = roleRepository;
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
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("roleNames") String roleNames, RedirectAttributes redirectAttributes) {
        Set<Role> roles = Arrays.stream(roleNames.split(","))
                .map(roleRepository::findByAuthority)
                .collect(Collectors.toSet());
        userService.saveUser(user, roles);
        redirectAttributes.addFlashAttribute("successMessage", "Roles saved successfully for user with ID: " + user.getId());
        return "redirect:/admin/users";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("usId") int id, @RequestParam(name = "role", required = false) String role, Model model) {
        User user = userService.getUser(id);
        userService.showUserRole(id, role);
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("usId") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
