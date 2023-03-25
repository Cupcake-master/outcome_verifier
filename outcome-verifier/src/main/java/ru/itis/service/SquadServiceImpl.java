package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Squad;
import ru.itis.repository.SquadRepository;

import java.util.Optional;

@Service
public class SquadServiceImpl{

    private final SquadRepository squadRepository;

    @Autowired
    public SquadServiceImpl(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
    }

    public Optional<Squad> findByName(String name) {
        return squadRepository.findByName(name);
    }
}
