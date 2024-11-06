package ru.school_assistant_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.school_assistant_rest.model.StudentsLessons;
import ru.school_assistant_rest.model.Teachers;
import ru.school_assistant_rest.service.StudentsLessonsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentsLessons")
@Tag(name = "studentsLessons", description = "API для работы с StudentsLessons")
public class StudentsLessonsController {

    private final StudentsLessonsService studentsLessonsService;

    public StudentsLessonsController(StudentsLessonsService studentsLessonsService) {
        this.studentsLessonsService = studentsLessonsService;
    }

    @Operation(summary = "Get StudentsLessons", description = "Получить StudentsLessons по его id")
    @GetMapping("/{id}")
    public ResponseEntity<StudentsLessons> find(@PathVariable @Parameter(description = "Идентификатор StudentsLessons")Long id){
        Optional<StudentsLessons> studentsLessons = studentsLessonsService.findById(id);
        if(studentsLessons.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(studentsLessons.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get All StudentsLessons", description = "Получить список всех StudentsLessons")
    @GetMapping()
    public ResponseEntity<List<StudentsLessons>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(studentsLessonsService.findAll());
    }

    @Operation(summary = "Get By Lessons Id", description = "Получить список StudentsLessons по идентификатору урока")
    @GetMapping("/lessonsId={lessonsId}")
    public ResponseEntity<List<StudentsLessons>> findByLessonsId(@PathVariable @Parameter(description = "Идентификатор StudentsLessons")Long lessonsId){
        return ResponseEntity.status(HttpStatus.OK).body(studentsLessonsService.findByLessonsId(lessonsId));
    }

    @Operation(summary = "Get By Students Id", description = "Получить список StudentsLessons по идентификатору ученика")
    @GetMapping("/studenstId={studentsId}")
    public ResponseEntity<List<StudentsLessons>> findByStudentsId(@PathVariable @Parameter(description = "Идентификатор StudentsLessons")Long studentsId){
        return ResponseEntity.status(HttpStatus.OK).body(studentsLessonsService.findByStudentsId(studentsId));
    }

    @Operation(summary = "Get By Lessons Id And Students Id", description = "Получить список StudentsLessons по идентификатору урока и идентификатору ученика")
    @GetMapping("/lessonsId={lessonsId}/studentsId={studentsId}")
    public ResponseEntity<StudentsLessons> findByLessonsIdAndStudentsId(@PathVariable @Parameter(description = "Идентификатор StudentsLessons")Long lessonsId, @PathVariable @Parameter(description = "Идентификатор StudentsLessons")Long studentsId){
        return ResponseEntity.status(HttpStatus.OK).body(studentsLessonsService.findByLessonsIdAndStudentsId(lessonsId, studentsId));
    }

    @Operation(summary = "Create StudentsLessons", description = "Создать StudentsLessons")
    @PostMapping()
    public ResponseEntity<StudentsLessons> createStudentsLessons(@RequestBody @Parameter(description = "StudentsLessons") StudentsLessons studentsLessons){
        if(!studentsLessonsService.create(studentsLessons).equals(null)){
            return ResponseEntity.status(HttpStatus.CREATED).body(studentsLessons);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete StudentsLessons", description = "Удаление StudentsLessons")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор StudentsLessons") Long id){
        studentsLessonsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
