package com.vector.studentmanagementspring.controller;

import com.vector.studentmanagementspring.entity.Lesson;
import com.vector.studentmanagementspring.entity.User;
import com.vector.studentmanagementspring.entity.UserType;
import com.vector.studentmanagementspring.repository.LessonRepository;
import com.vector.studentmanagementspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final LessonRepository lessonRepository;

    @GetMapping("/students")
    public String studentsPage(ModelMap modelMap) {
        List<User> users = userService.findByUserType(UserType.STUDENT);
        modelMap.put("users", users);
        return "/user/students";
    }

    @GetMapping("/teachers")
    public String teachersPage(ModelMap modelMap) {
        List<User> users = userService.findByUserType(UserType.TEACHER);
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
    public String addUser(@ModelAttribute User user,
                          @RequestParam("image") MultipartFile multipartFile) throws IOException {
        userService.save(user,multipartFile);
        if (user.getUserType() == UserType.STUDENT) {
            return "redirect:/students";
        } else {
           return "redirect:/teachers";
       }
    }

    @GetMapping("/users/deleteStudents")
    public String deleteStudent(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/students";

    }
}
