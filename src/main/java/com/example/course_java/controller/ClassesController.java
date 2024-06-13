package com.example.course_java.controller;

import com.example.course_java.domain.*;
import com.example.course_java.domain.Classes;
import com.example.course_java.dto.ClassesDTO;
import com.example.course_java.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping
    public List<ClassesDTO> getAllClasses() {
        List<Classes> classes = classesService.getAllClasses();
        return classes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ClassesDTO> getClassesById(@PathVariable Long id) {
        Optional<Classes> classes = classesService.getClassesById(id);
        return classes.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClassesDTO createClasses(@RequestBody ClassesDTO classesDTO) {
        Classes classes = convertToEntity(classesDTO);
        Classes createdClasses = classesService.createClasses(classes);
        return convertToDTO(createdClasses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassesDTO> updateClasses(@PathVariable Long id, @RequestBody ClassesDTO classesDTO) {
        if (classesService.getClassesById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        classesDTO.setId(id);
        Classes classes = convertToEntity(classesDTO);
        Classes updatedClasses = classesService.updateClasses(classes);
        return ResponseEntity.ok(convertToDTO(updatedClasses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasses(@PathVariable Long id) {
        if (classesService.getClassesById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        classesService.deleteClasses(id);
        return ResponseEntity.noContent().build();
    }

    private ClassesDTO convertToDTO(Classes classes) {
        return new ClassesDTO(
                classes.getId(),
                classes.getName(),
                classes.getDescription(),
                classes.getDuration(),
                classes.getTrainers().getId(),
                classes.getGyms() != null ? classes.getGyms().getId() : null
        );
    }

    private Classes convertToEntity(ClassesDTO classesDTO) {
        Classes classes = new Classes();
        classes.setId(classesDTO.getId());
        classes.setName(classesDTO.getName());
        classes.setDescription(classesDTO.getDescription());
        classes.setDuration(classesDTO.getDuration());

        Trainers trainers = new Trainers();
        trainers.setId(classesDTO.getTrainers_id());
        classes.setTrainers(trainers);


        Gyms gyms = new Gyms();
        gyms.setId(classesDTO.getGyms_id());
        classes.setGyms(gyms);

        return classes;
    }
}

