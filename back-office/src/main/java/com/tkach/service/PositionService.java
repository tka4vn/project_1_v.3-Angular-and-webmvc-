package com.tkach.service;

import com.tkach.model.Position;
import com.tkach.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    public void delete(Position position
    ) {
        positionRepository.delete(position);
    }
}
