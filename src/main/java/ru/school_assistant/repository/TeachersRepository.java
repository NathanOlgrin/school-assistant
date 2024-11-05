package ru.school_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant.model.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {
}
