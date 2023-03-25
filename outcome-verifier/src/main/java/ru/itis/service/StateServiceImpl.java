package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.State;
import ru.itis.repository.StateRepository;

import java.util.Optional;

@Service
public class StateServiceImpl {

    private final StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public Optional<State> findByName(String name) {
        return stateRepository.findByName(name);
    }
}
