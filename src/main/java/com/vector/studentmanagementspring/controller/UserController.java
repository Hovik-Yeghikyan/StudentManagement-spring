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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/students")
    public String studentsPage(ModelMap modelMap) {
        List<User> users = userRepository.findByUserType(UserType.STUDENT);
        modelMap.put("users", users);
        return "/user/students";
    }

    @GetMapping("/teachers")
    public String teachersPage(ModelMap modelMap) {
        List<User> users = userRepository.findByUserType(UserType.TEACHER);
        modelMap.put("users", users);
        return "/user/teachers";
    }

    @GetMapping("/users/add")
    public String addUserPage(ModelMap modelMap) {
        List<Lesson> lessons = lessonRepository.findAll();
        modelMap.put("lessons", lessons);
        return "/user/addUsers";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        if (user.getUserType() == UserType.STUDENT) {
            return "redirect:/students";
        } else {
           return "redirect:/teachers";
       }
    }

    @GetMapping("/users/deleteStudents")
    public String deleteStudent(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/students";

    }
}
