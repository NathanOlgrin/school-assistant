package ru.school_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
}
