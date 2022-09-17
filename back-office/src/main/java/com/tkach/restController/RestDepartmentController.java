package com.tkach.restController;

import com.tkach.model.Department;
import com.tkach.repositories.DepartmentRepository;
import com.tkach.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/department")
public class RestDepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Iterable<Department>> getList(
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("name"));
        Iterable<Department> department = departmentRepository.findAll(sort);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> postAdd(
            @RequestBody Department department
    ) {
        departmentRepository.save(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Department> department = departmentRepository.findById(id);
        return new ResponseEntity<>(department.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Department> putEdit(
            @RequestBody Department department
    ) {
        departmentRepository.save(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Department department
    ) {
        departmentService.delete(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


