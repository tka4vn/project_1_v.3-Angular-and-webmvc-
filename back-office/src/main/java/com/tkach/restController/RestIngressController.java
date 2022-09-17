package com.tkach.restController;

import com.tkach.model.Ingress;
import com.tkach.model.Users;
import com.tkach.repositories.IngressRepository;
import com.tkach.repositories.UsersRepository;
import com.tkach.service.IngressService;
import com.tkach.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/ingress")
public class RestIngressController {

    @Autowired
    private IngressRepository ingressRepository;
    @Autowired
    private IngressService ingressService;

    @GetMapping
    public ResponseEntity<Iterable<Ingress>> getList(
    ) {
        Iterable<Ingress> ingress = ingressRepository.findAll();
        return new ResponseEntity<>(ingress, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ingress> postAdd(
            @RequestBody Ingress ingress
    ) {
        ingressRepository.save(ingress);
        return new ResponseEntity<>(ingress, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ingress> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Ingress> ingress = ingressRepository.findById(id);
        return new ResponseEntity<>(ingress.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingress> putEdit(
            @RequestBody Ingress ingress
    ) {
        ingressRepository.save(ingress);
        return new ResponseEntity<>(ingress, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Ingress ingress
    ) {
        ingressService.delete(ingress);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
