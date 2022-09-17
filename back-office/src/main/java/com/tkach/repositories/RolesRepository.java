package com.tkach.repositories;

import com.tkach.model.Roles;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Integer> {
    Iterable<Roles> findAll(Sort sort);
}
