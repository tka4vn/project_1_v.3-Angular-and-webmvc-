package com.tkach.service;

import com.tkach.model.Request;
import com.tkach.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Transactional
    public void delete(Request request
    ) {
        requestRepository.delete(request);
    }
}
