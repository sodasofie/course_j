package com.example.course_java.controller;

import com.example.course_java.domain.Trainers;
import com.example.course_java.repository.TrainersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

