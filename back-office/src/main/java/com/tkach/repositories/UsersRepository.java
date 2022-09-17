package com.tkach.repositories;

import com.tkach.model.Department;
import com.tkach.model.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Users findByName(String name);
    Iterable<Users> findAll(Sort sort);
}
