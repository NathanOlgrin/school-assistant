package ru.school_assistant_page.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.school_assistant_page.controller.dto.StudentsPageDto;
import ru.school_assistant_page.service.StudentsPageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsPageController {

    private final StudentsPageService studentsPageService;

    @GetMapping("{id}")
    public String getStudentIdPage(@PathVariable Long id, Model model){
        Optional<StudentsPageDto> studentsPageDto = studentsPageService.findById(id);
        if(studentsPageDto.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("student", studentsPageDto.get());
        return "student-id-page.html";
    }

    @GetMapping()
    public String getAllStudents(Model model){
        List<StudentsPageDto> studentsPageDtoList = studentsPageService.findAll();
        model.addAttribute("students", studentsPageDtoList);
        return "students-all-page.html";
    }
}