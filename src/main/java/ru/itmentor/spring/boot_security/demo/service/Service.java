package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface Service {
    public void saveUser(User user);

    public Optional<User> findByUsername(String username);

    public void deleteUser(Integer id);

    public List<User> showUsers();

    public UserDetails loadUserByUsername(String username);

    public User getUser(int id);
}
