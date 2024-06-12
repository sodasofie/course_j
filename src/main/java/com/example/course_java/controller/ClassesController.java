package com.example.course_java.controller;

import com.example.course_java.domain.Classes;
import com.example.course_java.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping
    public List<Classes> getAllClasses() {
        return classesService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> getClassesById(@PathVariable Long id) {
        Optional<Classes> classs = classesService.getClassesById(id);
        return classs.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Classes createClasses(@RequestBody Classes classes) {
        return classesService.createClasses(classes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classes> updateClasses(@PathVariable Long id, @RequestBody Classes classes) {
        if (classesService.getClassesById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        classes.setId(id);
        return ResponseEntity.ok(classesService.updateClasses(classes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasses(@PathVariable Long id) {
        if (classesService.getClassesById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        classesService.deleteClasses(id);
        return ResponseEntity.noContent().build();
    }
}
