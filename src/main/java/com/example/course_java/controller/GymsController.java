package com.example.course_java.controller;


import com.example.course_java.domain.Gyms;
import com.example.course_java.repository.GymsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gyms")
public class GymsController {
    private final GymsRepository gymsRepository;

    public GymsController(GymsRepository gymsRepository) {
        this.gymsRepository = gymsRepository;
    }

    @GetMapping("/")
    public List<Gyms> getAllGyms() {
        return gymsRepository.findAll();
    }
}

