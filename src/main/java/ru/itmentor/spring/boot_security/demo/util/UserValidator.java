package ru.itmentor.spring.boot_security.demo.util;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserDS;
@Component
public class UserValidator implements Validator {
    private final UserDS userDS;

    public UserValidator(UserDS userDS) {
        this.userDS = userDS;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDS.loadUserByUsername(user.getName());

        }catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("name","","Имя занято другим великим атлетом");
    }
}
