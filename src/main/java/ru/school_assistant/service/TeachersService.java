package ru.school_assistant.service;

import org.springframework.stereotype.Service;
import ru.school_assistant.model.Lessons;
import ru.school_assistant.model.Students;
import ru.school_assistant.model.Teachers;
import ru.school_assistant.repository.LessonsRepository;
import ru.school_assistant.repository.StudentsRepository;
import ru.school_assistant.repository.TeachersRepository;

import java.util.*;

@Service
public class TeachersService {

    private final TeachersRepository teachersRepository;
    private final LessonsRepository lessonsRepository;
    private final StudentsRepository studentsRepository;

    public TeachersService(TeachersRepository teachersRepository, LessonsRepository lessonsRepository, StudentsRepository studentsRepository) {
        this.teachersRepository = teachersRepository;
        this.lessonsRepository = lessonsRepository;
        this.studentsRepository = studentsRepository;
    }

    public Optional<Teachers> findById(Long id){ //поиск учителя по его id
        return teachersRepository.findById(id);
    }

    public Optional<Teachers> findByNumberOfClass(Long numberOfClass){ //поиск учителя (классного руководителя), по номеру класса
        return teachersRepository.findByNumberOfClass(numberOfClass);
    }

    public List<Teachers> findAll(){ //поиск всех учителей
        return teachersRepository.findAll();
    }

    public Teachers create(Teachers teachers){ //создание учителя
        return teachersRepository.save(teachers);
    }

    public List<String> findLesson(Long teacherId){ //поиск всех уроков (без дубликатов), которые ведёт учитель
        List<Lessons> lessons = lessonsRepository.findByTeacherId(teacherId);
        List<String> lessonsName = new ArrayList<>();
        for (Lessons lesson: lessons) {
            lessonsName.add(lesson.getName());
        }
        Set<String> set = new HashSet<>(lessonsName);
        lessonsName.clear();
        lessonsName.addAll(set);
        return lessonsName;
    }

    public void delete(Long id){ //удаление учителя
        teachersRepository.deleteById(id);
    }
}
