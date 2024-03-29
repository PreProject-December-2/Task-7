package ru.itmentor.spring.boot_security.demo.service;//package web.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.DAO.UserDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImp implements UserService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository repository;


    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder, RoleRepository repository) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }


    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void register(User user) {
        userDao.register(user);
    }

}
