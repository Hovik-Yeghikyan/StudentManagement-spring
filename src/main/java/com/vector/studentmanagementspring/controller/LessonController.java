package com.vector.studentmanagementspring.controller;

import com.vector.studentmanagementspring.entity.Lesson;
import com.vector.studentmanagementspring.entity.User;
import com.vector.studentmanagementspring.entity.UserType;
import com.vector.studentmanagementspring.service.LessonService;
import com.vector.studentmanagementspring.service.UserService;
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

    private final UserService userService;

    private final LessonService lessonService;

    @GetMapping()
    public String lessonsPage(ModelMap modelMap) {
        List<Lesson> lessons = lessonService.findAll();
        modelMap.put("lessons", lessons);
        return "/lesson/lessons";
    }

    @GetMapping("/add")
    public String addLessonPage(ModelMap modelMap) {
        List<User> users = userService.findByUserType(UserType.TEACHER);
        modelMap.put("users", users);
        return "/lesson/addLesson";
    }

    @PostMapping("/add")
    public String addLesson(@ModelAttribute Lesson lesson, @RequestParam("user.id") int teacherId) {
        Optional<User> byId = userService.findById(teacherId);
        byId.ifPresent(lesson::setTeacher);
        lessonService.save(lesson);
        return "redirect:/lessons";

    }
}
