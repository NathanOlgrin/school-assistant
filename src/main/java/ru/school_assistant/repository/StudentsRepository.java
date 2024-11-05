package ru.school_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant.model.Students;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
    List<Students> findByNumberOfClass(Long numberOfClass);
}
