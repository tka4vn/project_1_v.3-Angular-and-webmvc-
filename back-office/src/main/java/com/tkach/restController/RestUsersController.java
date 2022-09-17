package com.tkach.restController;

import com.tkach.light.modelUsers;
import com.tkach.model.Users;
import com.tkach.repositories.UsersRepository;
import com.tkach.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class RestUsersController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<Iterable<Users>> getList(
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("name"));
        Iterable<Users> users = usersRepository.findAll(sort);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Users> postAdd(
            @RequestBody Users users
    ) {
        usersRepository.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Users> users = usersRepository.findById(id);
        return new ResponseEntity<>(users.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Users> putEdit(
            @RequestBody Users users
    ) {
        usersRepository.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Users users
    ) {
        usersService.delete(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
