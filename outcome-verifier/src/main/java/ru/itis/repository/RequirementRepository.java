package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.model.Grade;
import ru.itis.model.Module;
import ru.itis.model.Requirement;

import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    @Query(value = "SELECT r FROM Requirement r WHERE r.module_id = :module AND r.grade_id = :grade")
    Optional<Requirement> findByModuleAndGrade(Module module, Grade grade);
}
