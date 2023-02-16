package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.model.State;

import java.util.Optional;

@Service
public interface StateService {
    Optional<State> findByName(String name);
}
