package ru.school_assistant_page.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.school_assistant_page.controller.dto.LessonsPageDto;
import ru.school_assistant_page.service.LessonsPageService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonsPageController {

    private final LessonsPageService lessonsPageService;

    @GetMapping("{id}")
    public String getLessonIdPage(@PathVariable Long id, Model model){
        Optional<LessonsPageDto> lessonsPageDto = lessonsPageService.findById(id);
        if(lessonsPageDto.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("lesson", lessonsPageDto.get());
        return "lesson-id-page.html";
    }

    @GetMapping()
    public String getAllLessons(Model model){
        List<LessonsPageDto> lessonsPageDtoList = lessonsPageService.findAll();
        model.addAttribute("lessons", lessonsPageDtoList);
        return "lessons-all-page.html";
    }
}
