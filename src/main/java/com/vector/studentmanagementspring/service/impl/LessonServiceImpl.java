package com.vector.studentmanagementspring.service.impl;

import com.vector.studentmanagementspring.entity.Lesson;
import com.vector.studentmanagementspring.repository.LessonRepository;
import com.vector.studentmanagementspring.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson findLessonByTeacherId(int id) {
        return lessonRepository.findLessonByTeacherId(id);
    }
}
