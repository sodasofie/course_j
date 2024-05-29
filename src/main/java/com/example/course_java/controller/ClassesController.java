package com.example.course_java.controller;

import com.example.course_java.domain.Classes;
import com.example.course_java.repository.ClassesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    private final ClassesRepository classesRepository;

    public ClassesController(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    @GetMapping("/")
    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }
}
