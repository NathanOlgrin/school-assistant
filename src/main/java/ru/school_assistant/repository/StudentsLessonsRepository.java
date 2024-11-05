package ru.school_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant.model.StudentsLessons;

import java.util.Optional;

@Repository
public interface StudentsLessonsRepository extends JpaRepository<StudentsLessons, Long> {
    Optional<StudentsLessons> findByLessonId(Long lessonId);

    StudentsLessons findByLessonIdAndStudentId(Long id, Long studentsId);
}
