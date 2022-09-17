package com.tkach.repositories;

import com.tkach.model.Employee;
import com.tkach.model.Services;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServicesRepository extends CrudRepository<Services, Integer> {
    Iterable<Services> findAll(Sort sort);
}
