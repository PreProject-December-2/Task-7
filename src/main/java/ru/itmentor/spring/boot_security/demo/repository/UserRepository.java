package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Optional;

    @Repository
    public interface UserRepository extends CrudRepository<User, Integer> {


    Optional<User> findByName(String username);

}
