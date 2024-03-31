package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findByUsername(String username);

    public void saveUser(User user);

    public void deleteUser(Integer id);

    public List<User> showUsers();

    public User getUser(int id);

    public void showUsersRoles(Integer id, String roles);
}
