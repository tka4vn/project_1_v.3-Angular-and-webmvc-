package com.tkach.restController;

import com.tkach.model.Employee;
import com.tkach.repositories.EmployeeRepository;
import com.tkach.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/employee")
public class RestEmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Iterable<Employee>> getList(
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("fullName"));
        Iterable<Employee> employee = employeeRepository.findAll(sort);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> postAdd(
            @RequestBody Employee employee
    ) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> putEdit(
            @RequestBody Employee employee
    ) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Employee employee
    ) {
        employeeService.delete(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
