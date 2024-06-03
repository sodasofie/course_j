package com.example.course_java.controller;

import com.example.course_java.domain.Classes;
import com.example.course_java.repository.ClassesRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Classes getClassesById(@PathVariable Long id) {
        return classesRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Classes createClasses(@RequestBody Classes classes) {
        return classesRepository.save(classes);
    }

    @PutMapping("/{id}")
    public Classes updateClasses(@PathVariable Long id, @RequestBody Classes classes) {
        classes.setId(id);
        return classesRepository.save(classes);
    }

    @DeleteMapping("/{id}")
    public void deleteClasses(@PathVariable Long id) {
        classesRepository.deleteById(id);
    }
}
