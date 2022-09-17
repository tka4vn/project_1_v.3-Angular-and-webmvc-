package com.tkach.repositories;

import com.tkach.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    List<Status> findByName(String name);
}
