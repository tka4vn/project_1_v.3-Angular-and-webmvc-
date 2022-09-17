package com.tkach.repositories;

import com.tkach.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    List<Appointment> findByDateApp(String dateApp);
}
