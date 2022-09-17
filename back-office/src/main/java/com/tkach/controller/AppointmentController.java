package com.tkach.controller;

import com.tkach.model.*;
import com.tkach.repositories.AppointmentRepository;
import com.tkach.repositories.DepartmentRepository;
import com.tkach.repositories.EmployeeRepository;
import com.tkach.repositories.PositionRepository;
import com.tkach.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String appointmentList(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<Appointment> appointment = appointmentRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            appointment = appointmentRepository.findByDateApp(filter);
        } else {
            appointment = appointmentRepository.findAll();
        }

        model.addAttribute("appointment", appointment);
        model.addAttribute("filter", filter);

        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("employee", employee);
        Iterable<Department> department = departmentRepository.findAll();
        model.addAttribute("department", department);
        Iterable<Position> position = positionRepository.findAll();
        model.addAttribute("position", position);

        return "appointment/index";
    }

    @PostMapping
    public String addAppointment(
            @RequestParam String dateApp,
            @RequestParam Position idAppPos,
            @RequestParam Department idAppDep,
            @RequestParam Employee idAppEmp,
            Map<String, Object> model
    ) {
        Appointment appointment = new Appointment(dateApp, idAppDep, idAppPos, idAppEmp);
        appointmentRepository.save(appointment);

        Iterable<Appointment> appointments = appointmentRepository.findAll();
        model.put("appointment", appointments);
        return "redirect:/appointment";
    }

    @GetMapping("{appointment}")
    public String editAppointment(
            @PathVariable Appointment appointment,
            Model model
    ) {
        model.addAttribute("appointment", appointment);

        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("employee", employee);
        Iterable<Department> department = departmentRepository.findAll();
        model.addAttribute("department", department);
        Iterable<Position> position = positionRepository.findAll();
        model.addAttribute("position", position);

        return "appointment/edit";
    }

    @PostMapping("/edit")
    public String editSaveAppointment(
            @RequestParam String dateApp,
            @RequestParam Department idAppDep,
            @RequestParam Position idAppPos,
            @RequestParam Employee idAppEmp,
            @RequestParam("appointmentId") Appointment appointment
    ) {
        appointment.setDateApp(dateApp);
        appointment.setIdAppDep(idAppDep);
        appointment.setIdAppPos(idAppPos);
        appointment.setIdAppEmp(idAppEmp);
        appointmentRepository.save(appointment);
        return "redirect:/appointment";
    }

    @GetMapping("delete/{appointment}")
    public String delete(
            @PathVariable Appointment appointment
    ) {
        appointmentService.delete(appointment);
        return "redirect:/appointment";
    }
}