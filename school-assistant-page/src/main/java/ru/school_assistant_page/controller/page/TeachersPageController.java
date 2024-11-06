package ru.school_assistant_page.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.school_assistant_page.controller.dto.TeachersPageDto;
import ru.school_assistant_page.service.TeachersPageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersPageController {

    private final TeachersPageService teachersPageService;

    @GetMapping("{id}")
    public String getTeacherIdPage(@PathVariable Long id, Model model){
        Optional<TeachersPageDto> teachersPageDto = teachersPageService.findById(id);
        if(teachersPageDto.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("teacher", teachersPageDto.get());
        return "teacher-id-page.html";
    }

    @GetMapping()
    public String getAllTeachers(Model model){
        List<TeachersPageDto> teachersPageDtoList = teachersPageService.findAll();
        model.addAttribute("teachers", teachersPageDtoList);
        return "teachers-all-page.html";
    }
}
