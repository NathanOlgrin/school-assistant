package ru.school_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant.model.TeachersLessons;

@Repository
public interface TeachersLessonsRepository extends JpaRepository<TeachersLessons, Long> {

}
