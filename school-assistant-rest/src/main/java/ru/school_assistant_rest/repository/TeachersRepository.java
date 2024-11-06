package ru.school_assistant_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant_rest.model.Teachers;

import java.util.Optional;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {
    Optional<Teachers> findByNumberOfClass(Long numberOfClass);
}
