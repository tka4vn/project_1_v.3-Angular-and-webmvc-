package com.tkach.service;

import com.tkach.model.Ingress;
import com.tkach.repositories.IngressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngressService {
    @Autowired
    private IngressRepository ingressRepository;

    @Transactional
    public void delete(Ingress ingress
    ) {
        ingressRepository.delete(ingress);
    }
}
