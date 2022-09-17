package com.tkach.service;

import com.tkach.model.Employee;
import com.tkach.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void delete(Employee employee
    ) {
        employeeRepository.delete(employee);
    }
}
