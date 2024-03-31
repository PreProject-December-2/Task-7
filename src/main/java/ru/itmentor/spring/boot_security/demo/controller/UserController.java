package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping()
    public String userProfile(Model model, Principal principal) {
        String name = principal.getName();
        Optional<User> userOptional = userServiceImpl.findByUsername(name);
        if ((userOptional.isPresent())) {
            User user = userOptional.get();
            model.addAttribute("user", user);
        }
        return "user";
    }

}
