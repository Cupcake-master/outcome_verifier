package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.model.Squad;

import java.util.Optional;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
    Optional<Squad> findByName(String name);
}
