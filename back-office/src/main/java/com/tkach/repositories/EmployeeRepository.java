package com.tkach.repositories;

import com.tkach.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
        List<Employee> findByFullName(String fullName);

    Iterable<Employee> findAll(Sort sort);
}
