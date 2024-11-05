package ru.school_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant.model.StudentsLessons;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsLessonsRepository extends JpaRepository<StudentsLessons, Long> {
    StudentsLessons findByLessonsIdAndStudentsId(Long lessonsId, Long studentsId);

    List<StudentsLessons> findByLessonsId(Long lessonsId);

    List<StudentsLessons> findByStudentsId(Long studentsId);
}
