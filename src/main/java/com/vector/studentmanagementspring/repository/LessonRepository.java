package com.vector.studentmanagementspring.repository;

import com.vector.studentmanagementspring.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Lesson findLessonByTeacherId(int id);
}
