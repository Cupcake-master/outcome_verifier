package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.model.StatusTask;

@Repository
public interface StatusTaskRepository extends JpaRepository<StatusTask, Long> {
}
