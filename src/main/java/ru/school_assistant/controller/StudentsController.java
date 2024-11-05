package ru.school_assistant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school_assistant.model.Students;
import ru.school_assistant.model.StudentsLessons;
import ru.school_assistant.model.Teachers;
import ru.school_assistant.service.StudentsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@Tag(name = "Students", description = "API для работы с учениками")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Operation(summary = "Get Students", description = "Получить ученика по его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Students> find(@PathVariable @Parameter(description = "Идентификатор ученика") Long id){
        Optional<Students> students = studentsService.findById(id);
        if(students.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(students.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All Students", description = "Получить список всех учеников")
    @GetMapping()
    public ResponseEntity<List<Students>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(studentsService.findAll());
    }

    @Operation(summary = "Get Students By Number Of Class", description = "Получить список всех учеников в классе")
    @GetMapping("?numberOfClass")
    public ResponseEntity<List<Students>> findStudentsByNumberOfClass(@PathVariable @Parameter(description = "Номер класса") Long numberOfClass){
        return ResponseEntity.status(HttpStatus.OK).body(studentsService.findByNumberOfClass(numberOfClass));
    }

    @Operation(summary = "Get Assesment Students By Lesson Id", description = "Получить оценку ученика по идентификатору урока")
    @GetMapping("?lessonId")
    public ResponseEntity<StudentsLessons> findByLessonId(@PathVariable @Parameter(description = "Идентификатор урока") Long id){
        Optional<StudentsLessons> students = studentsService.findByLessonId(id);
        if(students.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(students.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get List Assesment Students By Lesson Id", description = "Получить все оценки ученика по названию урока и идентификатору ученика")
    @GetMapping("?lessonName&studentsId")
    public ResponseEntity<List<StudentsLessons>> findByLessonName(@PathVariable @Parameter(description = "Название предмета") String lessonName, @PathVariable @Parameter(description = "Идентификатор ученика") Long studentsId){
        return ResponseEntity.status(HttpStatus.OK).body(studentsService.findByLessonsName(lessonName, studentsId));
    }

    @Operation(summary = "Create Students", description = "Создать ученика")
    @PostMapping()
    public ResponseEntity<Students> createStudents(@RequestBody @Parameter(description = "Ученик") Students students){
        if(!studentsService.create(students).equals(null)){
            return ResponseEntity.status(HttpStatus.CREATED).body(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete Students", description = "Удаление ученика")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор ученика") Long id){
        studentsService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
