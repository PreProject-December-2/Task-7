package ru.itmentor.spring.boot_security.demo.DAO;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository repository;

    public UserDaoimpl(PasswordEncoder passwordEncoder, RoleRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = entityManager.createQuery("from User ", User.class).getResultList();
        users.forEach(user -> System.out.println(user.getName()));
        return users;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public void register(User user) {
        Role roleUser = repository.findByAuthority("ROLE_USER");
        user.setRoles(List.of(roleUser));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
        entityManager.flush();
    }


}

