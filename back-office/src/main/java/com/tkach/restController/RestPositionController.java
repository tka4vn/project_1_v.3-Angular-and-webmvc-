package com.tkach.restController;

import com.tkach.model.Position;
import com.tkach.repositories.PositionRepository;
import com.tkach.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/position")
public class RestPositionController {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionService positionService;

    @GetMapping
    public ResponseEntity<Iterable<Position>> getList(
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("name"));
        Iterable<Position> position = positionRepository.findAll(sort);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Position> postAdd(
            @RequestBody Position position
    ) {
        positionRepository.save(position);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Position> getEdit(
            @PathVariable("id") int id
    ) {
        Optional<Position> position = positionRepository.findById(id);
        return new ResponseEntity<>(position.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Position> putEdit(
            @RequestBody Position position
    ) {
        positionRepository.save(position);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Position position
    ) {
        positionService.delete(position);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
