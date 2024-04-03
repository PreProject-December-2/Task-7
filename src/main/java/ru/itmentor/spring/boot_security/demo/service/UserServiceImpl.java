package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Transactional
    public void saveUser(User user, Set<Role> roles) {
        Set<Role> userRoles = new HashSet<>();
        for (Role role : roles) {
            Role retrievedRole = roleRepository.findByAuthority(role.getAuthority());
            if (retrievedRole != null) {
                userRoles.add(retrievedRole);
            } else {
                throw new IllegalArgumentException("Role not found: " + role.getAuthority());
            }
        }
        user.setRoles(userRoles);
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setPassword(existingUser.getPassword());
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> showUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void showUserRole(Integer id, String roleName) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = roleRepository.findByAuthority(roleName);
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(role);
        user.setRoles(allRoles);
        userRepository.save(user);
    }
}




