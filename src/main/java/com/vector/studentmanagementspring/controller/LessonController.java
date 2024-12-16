package com.vector.studentmanagementspring.controller;

import com.vector.studentmanagementspring.entity.Lesson;
import com.vector.studentmanagementspring.entity.User;
import com.vector.studentmanagementspring.entity.UserType;
import com.vector.studentmanagementspring.repository.LessonRepository;
import com.vector.studentmanagementspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("lessons")
public class LessonController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping
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
    public String addLesson(@ModelAttribute Lesson lesson) {
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }
}
