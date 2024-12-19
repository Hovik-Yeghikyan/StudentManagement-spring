package com.vector.studentmanagementspring.controller;

import com.vector.studentmanagementspring.entity.Lesson;
import com.vector.studentmanagementspring.entity.User;
import com.vector.studentmanagementspring.entity.UserType;
import com.vector.studentmanagementspring.repository.LessonRepository;
import com.vector.studentmanagementspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final UserRepository userRepository;

    private final LessonRepository lessonRepository;

    @GetMapping()
    public String lessonsPage(ModelMap modelMap) {
        List<Lesson> lessons = lessonRepository.findAll();
        modelMap.put("lessons", lessons);
        return "/lesson/lessons";
    }

    @GetMapping("/add")
    public String addLessonPage(ModelMap modelMap) {
        List<User> users = userRepository.findByUserType(UserType.TEACHER);
        modelMap.put("users", users);
        return "/lesson/addLesson";
    }

    @PostMapping("/add")
    public String addLesson(@ModelAttribute Lesson lesson, @RequestParam("user.id") int teacherId) {
        Optional<User> byId = userRepository.findById(teacherId);
        byId.ifPresent(lesson::setTeacher);
        lessonRepository.save(lesson);
        return "redirect:/lessons";

    }
}
