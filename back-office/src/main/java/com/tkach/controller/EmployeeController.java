package com.tkach.controller;

import com.tkach.model.Employee;
import com.tkach.repositories.EmployeeRepository;
import com.tkach.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String main(
            Map<String, Object> model
    ) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.put("employees", employees);

        return "employee/index";
    }

    @PostMapping
    public String add(@RequestParam String fullName, Map<String, Object> model
    ) {
        Employee employee = new Employee(fullName);
        employeeRepository.save(employee);

        Iterable<Employee> employees = employeeRepository.findAll();
        model.put("employees", employees);

        return "employee/index";

    }

    @GetMapping("{employee}")
    public String employeeEditForm(
            @PathVariable Employee employee,
            Model model
    ) {
        model.addAttribute("employee", employee);

        return "employee/edit";
    }

    @PostMapping("/edit")
    public String employeeSave(
            @RequestParam String fullName,
            @RequestParam Map<String, String> form,
            @RequestParam("employeeId") Employee employee
    ) {
        employee.setFullName(fullName);
        employeeRepository.save(employee);

        return "redirect:/employee";
    }

    @GetMapping("delete/{employee}")
    public String delete(
            @PathVariable Employee employee
    ) {
        employeeService.delete(employee);
        return "redirect:/employee";
    }
}
