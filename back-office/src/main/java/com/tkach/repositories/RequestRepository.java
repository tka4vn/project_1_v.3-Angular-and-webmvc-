package com.tkach.repositories;

import com.tkach.model.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    List<Request> findByDateReq(String dateReq);

}
