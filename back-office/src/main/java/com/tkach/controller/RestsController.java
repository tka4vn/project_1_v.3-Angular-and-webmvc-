package com.tkach.controller;

import com.tkach.model.Services;
import com.tkach.model.Status;
import com.tkach.repositories.ServicesRepository;
import com.tkach.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/testj")
public class RestsController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private StatusRepository statusRepository;

//    @GetMapping
//    public ResponseEntity<Iterable<Services>> getAllServices(
//    ) {
//        Iterable<Services> services = servicesRepository.findAll();
//        return new ResponseEntity<>(services, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Iterable<Status>> getAllStatus(
    ) {
        Iterable<Status> status = statusRepository.findAll();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
