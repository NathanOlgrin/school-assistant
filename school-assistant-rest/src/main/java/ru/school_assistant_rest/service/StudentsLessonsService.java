package ru.school_assistant_rest.service;

import org.springframework.stereotype.Service;
import ru.school_assistant_rest.model.StudentsLessons;
import ru.school_assistant_rest.model.Teachers;
import ru.school_assistant_rest.repository.StudentsLessonsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsLessonsService {

    private final StudentsLessonsRepository studentsLessonsRepository;

    public StudentsLessonsService(StudentsLessonsRepository studentsLessonsRepository) {
        this.studentsLessonsRepository = studentsLessonsRepository;
    }

    public Optional<StudentsLessons> findById(Long id){ //поиск StudentsLessons по его id
        return studentsLessonsRepository.findById(id);
    }

    public List<StudentsLessons> findAll(){ //поиск всех учителей
        return studentsLessonsRepository.findAll();
    }

    public List<StudentsLessons> findByLessonsId(Long lessonsId){
        return studentsLessonsRepository.findByLessonsId(lessonsId);
    }

    public List<StudentsLessons> findByStudentsId(Long studentsId){
        return studentsLessonsRepository.findByStudentsId(studentsId);
    }

    public StudentsLessons findByLessonsIdAndStudentsId(Long lessonsId, Long studentsId){
        return studentsLessonsRepository.findByLessonsIdAndStudentsId(lessonsId, studentsId);
    }

    public StudentsLessons create(StudentsLessons studentsLessons){ //создание StudentsLessons
        return studentsLessonsRepository.save(studentsLessons);
    }

    public void delete(Long id){ //удаление учителя
        studentsLessonsRepository.deleteById(id);
    }
}
