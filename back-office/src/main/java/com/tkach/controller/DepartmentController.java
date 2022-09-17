package com.tkach.controller;

import com.tkach.model.Department;
import com.tkach.model.Status;
import com.tkach.repositories.DepartmentRepository;
import com.tkach.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String main(
            Map<String, Object> model
    ) {
        Iterable<Department> departments = departmentRepository.findAll();
        model.put("departments", departments);

        return "department/index";
    }

    @PostMapping
    public String add(@RequestParam String name, Map<String, Object> model
    ) {
        Department department = new Department(name);
        departmentRepository.save(department);

        Iterable<Department> departments = departmentRepository.findAll();
        model.put("departments", departments);

        return "department/index";
    }

    @GetMapping("{department}")
    public String departmentEditForm(
            @PathVariable Department department,
            Model model
    ) {
        model.addAttribute("department", department);

        return "department/edit";
    }

    @PostMapping("/edit")
    public String departmentSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("departmentId") Department department
    ) {
        department.setName(name);
        departmentRepository.save(department);

        return "redirect:/department";
    }

    @GetMapping("delete/{department}")
    public String delete(
            @PathVariable Department department
    ) {
        departmentService.delete(department);
        return "redirect:/department";
    }

}