package ru.school_assistant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school_assistant.model.Lessons;
import ru.school_assistant.service.LessonsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
@Tag(name = "Lessons", description = "API для работы с уроками")
public class LessonsController {

    private final LessonsService lessonsService;

    public LessonsController(LessonsService lessonsService) {
        this.lessonsService = lessonsService;
    }

    @Operation(summary = "Get Lesson", description = "Получить урок по его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Lessons> find(@PathVariable @Parameter(description = "Идентификатор урока") Long id){
        Optional<Lessons> lesson = lessonsService.findById(id);
        if(lesson.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(lesson.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Lesson", description = "Получить список всех уроков")
    @GetMapping()
    public ResponseEntity<List<Lessons>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(lessonsService.findAll());
    }

    @Operation(summary = "Get Lessons in Day", description = "Получить список уроков всех классов в этот день")
    @GetMapping("/{daysOfWeek}")
    public ResponseEntity<List<Lessons>> findByDaysOfWeek(@PathVariable @Parameter(description = "День недели") String daysOfWeek){
        if(!lessonsService.findByDaysOfWeek(daysOfWeek).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(lessonsService.findByDaysOfWeek(daysOfWeek));
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Get Lessons of Class in Day", description = "Получить список уроков ОПРЕДЕЛЕННОГО класса в этот день")
    @GetMapping("/{daysOfWeek}&{numberOfClass}")
    public ResponseEntity<List<Lessons>> findByDaysOfWeekAndNumberOfClass(@PathVariable @Parameter(description = "День недели") String daysOfWeek, @PathVariable @Parameter(description = "Номер класса") Long numberOfClass){
        if(!lessonsService.findByDaysOfWeekAndNumberOfClass(daysOfWeek, numberOfClass).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(lessonsService.findByDaysOfWeekAndNumberOfClass(daysOfWeek, numberOfClass));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create lesson", description = "Создать урок")
    @PostMapping()
    public ResponseEntity<Lessons> create(@RequestBody @Parameter(description = "Урок") Lessons lessons){
        if(!lessonsService.create(lessons).equals(null)){
            return ResponseEntity.status(HttpStatus.CREATED).body(lessons);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete lesson", description = "Удалить урок")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор урока") Long id){
        lessonsService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
