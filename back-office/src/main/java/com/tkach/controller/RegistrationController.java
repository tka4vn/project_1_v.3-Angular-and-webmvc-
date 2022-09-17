package com.tkach.controller;

import com.tkach.model.Role;
import com.tkach.model.Users;
import com.tkach.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUsers(Users users, Map<String, Object> model
    ) {
        Users usersFromDb = usersRepository.findByName(users.getName());

        if (usersFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        usersRepository.save(users);

        return "redirect:/login";
    }
}
