package com.example.course_java.controller;

import com.example.course_java.domain.Trainers;
import com.example.course_java.repository.TrainersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainersController {
    private final TrainersRepository trainersRepository;

    public TrainersController(TrainersRepository trainersRepository) {
        this.trainersRepository = trainersRepository;
    }

    @GetMapping("/")
    public List<Trainers> getAllTrainers() {
        return trainersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Trainers getTrainersById(@PathVariable Long id) {
        return trainersRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Trainers createTrainers(@RequestBody Trainers trainers) {
        return trainersRepository.save(trainers);
    }

    @PutMapping("/{id}")
    public Trainers updateTrainers(@PathVariable Long id, @RequestBody Trainers trainers) {
        trainers.setId(id);
        return trainersRepository.save(trainers);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainers(@PathVariable Long id) {
        trainersRepository.deleteById(id);
    }
}

