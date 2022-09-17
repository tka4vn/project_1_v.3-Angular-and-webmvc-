package com.tkach.controller;

import com.tkach.model.Request;
import com.tkach.model.Services;
import com.tkach.model.Users;
import com.tkach.repositories.RequestRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public String index(
            Map<String, Object> model
    ) {
        Iterable<Services> services = servicesRepository.findAll();
        model.put("services", services);

        Iterable<Request> request = requestRepository.findAll();
        model.put("request", request);

        Iterable<Users> users = usersRepository.findAll();
        model.put("users", users);

        return "index";
    }
    @GetMapping("/login")
    public String login(
            Map<String, Object> model
    ) {
        Iterable<Users> users = usersRepository.findAll();
        model.put("users", users);

        return "index";
    }

    @GetMapping("/test")
    public String test(
            Map<String, Object> model
    ) {
        Iterable<Services> services = servicesRepository.findAll();
        model.put("services", services);
        return "test";
    }

}