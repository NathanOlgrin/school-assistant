package ru.school_assistant_rest.service;

import org.springframework.stereotype.Service;
import ru.school_assistant_rest.model.Lessons;
import ru.school_assistant_rest.model.Students;
import ru.school_assistant_rest.model.StudentsLessons;
import ru.school_assistant_rest.repository.LessonsRepository;
import ru.school_assistant_rest.repository.StudentsLessonsRepository;
import ru.school_assistant_rest.repository.StudentsRepository;
import ru.school_assistant_rest.repository.TeachersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final LessonsRepository lessonsRepository;
    private final TeachersRepository teachersRepository;
    private final StudentsLessonsRepository studentsLessonsRepository;

    public StudentsService(StudentsRepository studentsRepository, LessonsRepository lessonsRepository, TeachersRepository teachersRepository, StudentsLessonsRepository studentsLessonsRepository) {
        this.studentsRepository = studentsRepository;
        this.lessonsRepository = lessonsRepository;
        this.teachersRepository = teachersRepository;
        this.studentsLessonsRepository = studentsLessonsRepository;
    }

    public Optional<Students> findById(Long id){ //поиск ученика по его id
        return studentsRepository.findById(id);
    }

    public List<Students> findAll(){ //поиск всех учеников
        return studentsRepository.findAll();
    }

    public List<Students> findByNumberOfClass(Long numberOfClass){
        return studentsRepository.findByNumberOfClass(numberOfClass);
    }

    public Students create(Students students){ //создание нового ученика
        return studentsRepository.save(students);
    }

    public void delete(Long id){ //удаление ученика по его id
        studentsRepository.deleteById(id);
    }

    public List<StudentsLessons> findByLessonsName(String lessonName, Long studentsId){ //поиск ВСЕХ оценок ученика по определенному предмету
        Long numberOfClass = studentsRepository.findById(studentsId).get().getNumberOfClass();  //поиск номера класса, в котором находится ученик, в таблице lessons
        List<Lessons> lessonsOfClass = lessonsRepository.findByLessonsNameAndNumberOfClass(lessonName, numberOfClass); //поиск ВСЕХ уроков с таким названием для этого класса
        List<StudentsLessons> studentsLessons = new ArrayList<>();
        for (Lessons lessons: lessonsOfClass) {
            studentsLessons.add(studentsLessonsRepository.findByLessonsIdAndStudentsId(lessons.getId(), studentsId));
        }
        return studentsLessons;
    }
}
