package ru.school_assistant.service;

import org.springframework.stereotype.Service;
import ru.school_assistant.model.Lessons;
import ru.school_assistant.repository.LessonsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonsService {

    private final LessonsRepository lessonsRepository;

    public LessonsService(LessonsRepository lessonsRepository) {
        this.lessonsRepository = lessonsRepository;
    }

    public Optional<Lessons> findById(Long id) {
        return lessonsRepository.findById(id);
    }

    public List<Lessons> findByDaysOfWeek(Enum dayOfWeek){
        return lessonsRepository.findByDaysOfWeek(dayOfWeek);
    }

    public List<Lessons> findAll(){
        return lessonsRepository.findAll();
    }

    public Lessons create(Lessons lessons){
        return lessonsRepository.save(lessons);
    }
    public void delete(Long id){
        lessonsRepository.deleteById(id);
    }
}
