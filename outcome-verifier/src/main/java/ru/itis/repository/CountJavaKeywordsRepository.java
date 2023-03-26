package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.model.CountJavaKeywords;

@Repository
public interface CountJavaKeywordsRepository extends JpaRepository<CountJavaKeywords, Long> {
}
