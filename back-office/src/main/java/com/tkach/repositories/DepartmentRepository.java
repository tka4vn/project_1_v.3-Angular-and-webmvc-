package com.tkach.repositories;

import com.tkach.model.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    Iterable<Department> findAll(Sort sort);
//        List<Department> findByName(String name);

}
