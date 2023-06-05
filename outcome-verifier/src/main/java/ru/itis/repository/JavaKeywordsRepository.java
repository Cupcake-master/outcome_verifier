package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.model.Grade;
import ru.itis.model.JavaKeywords;
import ru.itis.model.Module;
import ru.itis.model.Requirement;

import java.util.List;
import java.util.Optional;

@Repository
public interface JavaKeywordsRepository extends JpaRepository<JavaKeywords, Long> {
    List<JavaKeywords> findAll();
    Optional<JavaKeywords> findByName(String name);

    @Query(value = "SELECT r FROM JavaKeywords r WHERE r.module_id = :module AND r.grade_id = :grade")
    List<JavaKeywords> findByModuleAndGrade(Module module, Grade grade);
}
