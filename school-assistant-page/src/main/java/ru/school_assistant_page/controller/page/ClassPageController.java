package ru.school_assistant_page.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.school_assistant_page.controller.dto.ClassPageDto;
import ru.school_assistant_page.service.ClassPageService;

import java.util.Optional;

@Controller
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassPageController {

    private final ClassPageService classPageService;

    @GetMapping("{id}")
    public String getByNumberOfClass(@PathVariable Long id, Model model) {
        {
            Optional<ClassPageDto> classPageDto = classPageService.findByNumberOfClass(id);
            if (classPageDto.isEmpty()) {
                return "not-found.html";
            }
            model.addAttribute("class", classPageDto.get());
            return ("class-page.html");
        }
    }
}
