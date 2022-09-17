package com.tkach.restController;

import com.tkach.model.Department;
import com.tkach.model.Roles;
import com.tkach.model.Services;
import com.tkach.repositories.DepartmentRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.service.DepartmentService;
import com.tkach.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/services")
public class RestServicesController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public ResponseEntity<Iterable<Services>> getList(
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("name"));
        Iterable<Services> services = servicesRepository.findAll(sort);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Services> postAdd(
            @RequestBody Services services
    ) {
        servicesRepository.save(services);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Services> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Services> services = servicesRepository.findById(id);
        return new ResponseEntity<>(services.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Services> putEdit(
            @RequestBody Services services
    ) {

        servicesRepository.save(services);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PutMapping("addRoles/{services}/{roles}")
    public ResponseEntity<Services> addRoles(
            @PathVariable Services services,
            @PathVariable Roles roles
    ) {
        servicesService.addRoles(services, roles);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @PutMapping("removeRoles/{services}/{roles}")
    public ResponseEntity<Services> removeRoles(
            @PathVariable Services services,
            @PathVariable Roles roles
    ) {
        servicesService.removeRoles(services, roles);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Services services
    ) {
        servicesService.delete(services);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


