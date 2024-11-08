package ru.school_assistant_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.school_assistant_rest.model.DayOfWeek;
import ru.school_assistant_rest.model.Lessons;

import java.util.List;

@Repository
public interface LessonsRepository extends JpaRepository<Lessons, Long> {
    List<Lessons> findByDaysOfWeek(Enum dayOfWeek);

    List<Lessons> findByTeacherId(Long teacherId);

    List<Lessons> findByLessonsNameAndNumberOfClass(String lessonName, Long numberOfClass);

    List<Lessons> findByDaysOfWeekAndNumberOfClass(DayOfWeek dayOfWeek, Long numberOfClass);
}
