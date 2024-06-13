package com.example.course_java.controller;

import com.example.course_java.domain.Gyms;
import com.example.course_java.dto.GymsDTO;
import com.example.course_java.service.GymsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gyms")
public class GymsController {
    @Autowired
    private GymsService gymsService;

    @GetMapping
    public List<GymsDTO> getAllGyms() {
        List<Gyms> gyms = gymsService.getAllGyms();
        return gyms.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymsDTO> getGymsById(@PathVariable Long id) {
        Optional<Gyms> gyms = gymsService.getGymsById(id);
        return gyms.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GymsDTO createGyms(@RequestBody GymsDTO gymsDTO) {
        Gyms gyms = convertToEntity(gymsDTO);
        Gyms createdGyms = gymsService.createGyms(gyms);
        return convertToDTO(createdGyms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymsDTO> updateGyms(@PathVariable Long id, @RequestBody GymsDTO gymsDTO) {
        if (gymsService.getGymsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gymsDTO.setId(id);
        Gyms gyms = convertToEntity(gymsDTO);
        Gyms updatedGyms = gymsService.updateGyms(gyms);
        return ResponseEntity.ok(convertToDTO(updatedGyms));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGyms(@PathVariable Long id) {
        if (gymsService.getGymsById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gymsService.deleteGyms(id);
        return ResponseEntity.noContent().build();
    }

    private GymsDTO convertToDTO(Gyms gyms) {
        return new GymsDTO(
                gyms.getId(),
                gyms.getName(),
                gyms.getDescription(),
                gyms.getAddress()
        );
    }

    private Gyms convertToEntity(GymsDTO gymsDTO) {
        Gyms gyms = new Gyms();
        gyms.setId(gymsDTO.getId());
        gyms.setName(gymsDTO.getName());
        gyms.setDescription(gymsDTO.getDescription());
        gyms.setAddress(gymsDTO.getAddress());

        return gyms;
    }
}

