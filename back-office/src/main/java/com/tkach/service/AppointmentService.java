package com.tkach.service;

import com.tkach.model.Appointment;
import com.tkach.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public void delete(Appointment appointment
    ) {
        appointmentRepository.delete(appointment);
    }
}
