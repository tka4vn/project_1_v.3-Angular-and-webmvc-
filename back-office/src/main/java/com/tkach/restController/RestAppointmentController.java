package com.tkach.restController;

import com.tkach.model.Appointment;
import com.tkach.repositories.AppointmentRepository;
import com.tkach.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/appointment")
public class RestAppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> getList(
    ) {
        Iterable<Appointment> appointment = appointmentRepository.findAll();
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Appointment> postAdd(
            @RequestBody Appointment appointment
    ) {
        appointmentRepository.save(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Appointment> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Appointment> putEdit(
            @RequestBody Appointment appointment
    ) {
        appointmentRepository.save(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Appointment appointment
    ) {
        appointmentService.delete(appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
