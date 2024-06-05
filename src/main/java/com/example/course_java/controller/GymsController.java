package com.example.course_java.controller;

import com.example.course_java.domain.Gyms;
import com.example.course_java.service.GymsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gyms")
public class GymsController {
    @Autowired
    private GymsService gymsService;

    @GetMapping
    public List<Gyms> getAllGyms() {
        return gymsService.getAllGyms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gyms> getGymsById(@PathVariable Long id) {
        Optional<Gyms> gym = gymsService.getGymsById(id);
        return gym.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gyms createGyms(@RequestBody Gyms gyms) {
        return gymsService.createGyms(gyms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gyms> updateGyms(@PathVariable Long id, @RequestBody Gyms gyms) {
        if (gymsService.getGymsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gyms.setId(id); // Ensure the id is set
        return ResponseEntity.ok(gymsService.updateGyms(gyms));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGyms(@PathVariable Long id) {
        if (gymsService.getGymsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gymsService.deleteGyms(id);
        return ResponseEntity.noContent().build();
    }
}

