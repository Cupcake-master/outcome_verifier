package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.model.Squad;

import java.util.Optional;

@Service
public interface SquadService {
    Optional<Squad> findByName(String name);
}
