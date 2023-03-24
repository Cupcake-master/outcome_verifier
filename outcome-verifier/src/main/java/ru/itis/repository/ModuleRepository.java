package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
