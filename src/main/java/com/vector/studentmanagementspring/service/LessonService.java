package com.vector.studentmanagementspring.service;

import com.vector.studentmanagementspring.entity.Lesson;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    List<Lesson> findAll();

    Optional<Lesson> findById(int id);

    void save(Lesson lesson);

    Lesson findLessonByTeacherId(int id);
}
