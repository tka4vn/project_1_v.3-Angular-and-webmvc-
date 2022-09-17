package com.tkach.restController;

import com.tkach.model.Roles;
import com.tkach.model.Services;
import com.tkach.repositories.RolesRepository;
import com.tkach.repositories.ServicesRepository;
import com.tkach.service.RolesService;
import com.tkach.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/roles")
public class RestRolesController {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<Iterable<Roles>> getList(
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("name"));
        Iterable<Roles> roles = rolesRepository.findAll(sort);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Roles> postAdd(
            @RequestBody Roles roles
    ) {
        rolesRepository.save(roles);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Roles> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Roles> roles = rolesRepository.findById(id);
        return new ResponseEntity<>(roles.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Roles> putEdit(
            @RequestBody Roles roles
    ) {
        rolesRepository.save(roles);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping("addService/{roles}/{services}")
    public ResponseEntity<Roles> addRoles(
            @PathVariable Roles roles,
            @PathVariable Services services
    ) {
        rolesService.addServices(roles, services);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @PutMapping("removeService/{roles}/{services}")
    public ResponseEntity<Roles> removeRoles(
            @PathVariable Roles roles,
            @PathVariable Services services
    ) {
        rolesService.removeServices(roles, services);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Roles roles
    ) {
        rolesService.delete(roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


