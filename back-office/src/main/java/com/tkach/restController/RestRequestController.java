package com.tkach.restController;

import com.tkach.model.Appointment;
import com.tkach.model.Request;
import com.tkach.repositories.AppointmentRepository;
import com.tkach.repositories.RequestRepository;
import com.tkach.service.AppointmentService;
import com.tkach.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/request")
public class RestRequestController {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<Iterable<Request>> getList(
    ) {
        Iterable<Request> request = requestRepository.findAll();
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Request> postAdd(
            @RequestBody Request request
    ) {
        requestRepository.save(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Request> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Request> request = requestRepository.findById(id);
        return new ResponseEntity<>(request.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Request> putEdit(
            @RequestBody Request request
    ) {
        requestRepository.save(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Request request
    ) {
        requestRepository.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
