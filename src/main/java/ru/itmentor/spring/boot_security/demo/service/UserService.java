package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    public Optional<User> findByUsername(String username);

    public User saveUser(User user, Set<Role> roleNames);

    public void deleteUser(Integer id);

    public List<User> showUsers();

    public User getUser(int id);

    public void showUserRole(Integer id, String roleName);


}
