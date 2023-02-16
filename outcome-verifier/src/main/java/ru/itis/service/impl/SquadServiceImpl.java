package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Squad;
import ru.itis.repository.SquadRepository;
import ru.itis.service.SquadService;

import java.util.Optional;

@Service
public class SquadServiceImpl implements SquadService {

    private final SquadRepository squadRepository;

    @Autowired
    public SquadServiceImpl(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
    }

    @Override
    public Optional<Squad> findByName(String name) {
        return squadRepository.findByName(name);
    }
}
