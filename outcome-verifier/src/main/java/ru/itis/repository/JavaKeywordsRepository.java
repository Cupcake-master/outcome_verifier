package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.model.JavaKeywords;

import java.util.List;
import java.util.Optional;

@Repository
public interface JavaKeywordsRepository extends JpaRepository<JavaKeywords, Long> {
    List<JavaKeywords> findAll();
    Optional<JavaKeywords> findByName(String name);
}
