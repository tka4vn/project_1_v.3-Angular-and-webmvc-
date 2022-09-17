package com.tkach.repositories;

import com.tkach.model.Position;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {
        List<Position> findByName(String name);

    Iterable<Position> findAll(Sort sort);
}
