package com.example.course_java.controller;

import com.example.course_java.domain.Gyms;
import com.example.course_java.repository.GymsRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Gyms getGymsById(@PathVariable Long id) {
        return gymsRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Gyms createGyms(@RequestBody Gyms gyms) {
        return gymsRepository.save(gyms);
    }

    @PutMapping("/{id}")
    public Gyms updateGyms(@PathVariable Long id, @RequestBody Gyms gyms) {
        gyms.setId(id);
        return gymsRepository.save(gyms);
    }

    @DeleteMapping("/{id}")
    public void deleteGyms(@PathVariable Long id) {
        gymsRepository.deleteById(id);
    }
}

