package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements Service {

    private final UserService userService;

    public UserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }

    @Override
    public List<User> showUsers() {
        return userService.showUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userService.loadUserByUsername(username);
    }

    @Override
    public User getUser(int id) {
        return userService.getUser(id);
    }


}
