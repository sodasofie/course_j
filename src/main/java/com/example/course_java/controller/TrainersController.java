package com.example.course_java.controller;

import com.example.course_java.domain.Trainers;
import com.example.course_java.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainers")
public class TrainersController {
    @Autowired
    private TrainersService trainersService;

    @GetMapping
    public List<Trainers> getAllTrainers() {
        return trainersService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainers> getTrainersById(@PathVariable Long id) {
        Optional<Trainers> trainer = trainersService.getTrainersById(id);
        return trainer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trainers createTrainers(@RequestBody Trainers trainers) {
        return trainersService.createTrainers(trainers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainers> updateTrainers(@PathVariable Long id, @RequestBody Trainers trainers) {
        if (trainersService.getTrainersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        trainers.setId(id);
        return ResponseEntity.ok(trainersService.updateTrainers(trainers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainers(@PathVariable Long id) {
        if (trainersService.getTrainersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        trainersService.deleteTrainers(id);
        return ResponseEntity.noContent().build();
    }
}

