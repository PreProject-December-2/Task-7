package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserDS;

import java.security.Principal;

@Controller
public class UserController {
    private final UserDS userDS;

    public UserController(UserDS userDS) {
        this.userDS = userDS;
    }

    @GetMapping("/user")
    public String userProfile(Model model, Principal principal) {
        String name = principal.getName();
        User user = userDS.findByUsername(name);
        model.addAttribute("user", user);
        return "user";
    }
}
