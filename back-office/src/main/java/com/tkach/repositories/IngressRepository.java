package com.tkach.repositories;

import com.tkach.model.Ingress;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface IngressRepository extends CrudRepository<Ingress, Integer> {

}
