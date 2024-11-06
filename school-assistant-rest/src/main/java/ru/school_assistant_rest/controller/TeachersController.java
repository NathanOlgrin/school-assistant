package ru.school_assistant_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school_assistant_rest.model.Teachers;
import ru.school_assistant_rest.service.TeachersService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teachers", description = "API для работы с учителями")
public class TeachersController {
    private final TeachersService teachersService;

    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @Operation(summary = "Get Teacher", description = "Получить учителя по его id")
    @GetMapping("/{id}")
    public ResponseEntity<Teachers> find(@PathVariable @Parameter(description = "Идентификатор учителя")Long id){
        Optional<Teachers> teachers = teachersService.findById(id);
        if(teachers.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(teachers.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Teachers", description = "Получить список всех учителей")
    @GetMapping()
    public ResponseEntity<List<Teachers>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(teachersService.findAll());
    }

    @Operation(summary = "Get Teacher by Number Of Class", description = "Получить классного руководителя по номеру класса")
    @GetMapping("/numberOfClass={numberOfClass}")
    public ResponseEntity<Teachers> findByNumberOfClass(@PathVariable @Parameter(description = "Идентификатор учителя")Long numberOfClass){
        Optional<Teachers> teachers = teachersService.findByNumberOfClass(numberOfClass);
        if(teachers.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(teachers.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get Lessons by Teacher", description = "Поиск всех уроков, которые ведет учитель")
    @GetMapping("/{teacherId}/lessons")
    public ResponseEntity<List<String>> findLesson(@PathVariable @Parameter(description = "Идентификатор учителя") Long teacherId){
        return ResponseEntity.status(HttpStatus.OK).body(teachersService.findLesson(teacherId));
    }

    @Operation(summary = "Create Teacher", description = "Создать учителя")
    @PostMapping()
    public ResponseEntity<Teachers> createTeacher(@RequestBody @Parameter(description = "Учитель") Teachers teachers){
        if(!teachersService.create(teachers).equals(null)){
            return ResponseEntity.status(HttpStatus.CREATED).body(teachers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete Teacher", description = "Удаление учителя")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор учителя") Long id){
        teachersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
