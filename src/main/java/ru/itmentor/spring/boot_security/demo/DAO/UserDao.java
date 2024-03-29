package ru.itmentor.spring.boot_security.demo.DAO;//package web.DAO;


import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUser();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public void register(User user);

}
