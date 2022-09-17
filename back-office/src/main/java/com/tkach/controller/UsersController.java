package com.tkach.controller;

import com.tkach.model.Employee;
import com.tkach.model.Role;
import com.tkach.model.Users;
import com.tkach.repositories.EmployeeRepository;
import com.tkach.repositories.UsersRepository;
import com.tkach.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("listUsers", usersRepository.findAll());
        model.addAttribute("roles", Role.values());
        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("employee", employee);
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable Users user, Model model
    ) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping("edit/{userId}")
    public String userSave(
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam Employee idUseEmp,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") Users user
    ) {
        user.setName(name);
        user.setPassword(password);
        user.setIdUseEmp(idUseEmp);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        usersRepository.save(user);

        return "redirect:/user";
    }


    @GetMapping("delete/{user}")
    public String delete(
            @PathVariable Users user
    ) {
        usersService.delete(user);
        return "redirect:/user";
    }
}