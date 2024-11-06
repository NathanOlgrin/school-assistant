package ru.school_assistant_rest.service;

import org.springframework.stereotype.Service;
import ru.school_assistant_rest.model.DayOfWeek;
import ru.school_assistant_rest.model.Lessons;
import ru.school_assistant_rest.repository.LessonsRepository;

import java.util.*;

@Service
public class LessonsService {

    private final LessonsRepository lessonsRepository;

    public LessonsService(LessonsRepository lessonsRepository) {
        this.lessonsRepository = lessonsRepository;
    }

    public Optional<Lessons> findById(Long id) { //получить один определенный урок
        return lessonsRepository.findById(id);
    }

    public List<Lessons> findByDaysOfWeek(String day){ //поиск всех уроков всех классов за этот день
        Optional<DayOfWeek> dayOfWeek = Arrays.stream(DayOfWeek.values()).filter(d -> d.toString().equals(day)).findFirst();
        return lessonsRepository.findByDaysOfWeek(dayOfWeek.get());
    }

    public List<Lessons> findByDaysOfWeekAndNumberOfClass(String day, Long numberOfClass){ //поиск всех уроков определенного класса за этот день
        Optional<DayOfWeek> dayOfWeek = Arrays.stream(DayOfWeek.values()).filter(d -> d.toString().equals(day)).findFirst();
                return lessonsRepository.findByDaysOfWeekAndNumberOfClass(dayOfWeek.get(), numberOfClass);
    }

    public List<Lessons> findAll(){ //получить список всех уроков
        return lessonsRepository.findAll();
    }

    public Lessons create(Lessons lessons){ //создание урока
        return lessonsRepository.save(lessons);
    }
    public void delete(Long id){ //удаление урока
        lessonsRepository.deleteById(id);
    }
}
