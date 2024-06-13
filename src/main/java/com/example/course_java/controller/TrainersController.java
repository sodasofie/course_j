package com.example.course_java.controller;

import com.example.course_java.domain.Trainers;
import com.example.course_java.dto.TrainersDTO;
import com.example.course_java.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainers")
public class TrainersController {
    @Autowired
    private TrainersService trainersService;

    @GetMapping
    public List<TrainersDTO> getAllTrainers() {
        List<Trainers> trainers = trainersService.getAllTrainers();
        return trainers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainersDTO> getTrainersById(@PathVariable Long id) {
        Optional<Trainers> trainers = trainersService.getTrainersById(id);
        return trainers.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TrainersDTO createTrainers(@RequestBody TrainersDTO trainersDTO) {
        Trainers trainers = convertToEntity(trainersDTO);
        Trainers createdTrainers = trainersService.createTrainers(trainers);
        return convertToDTO(createdTrainers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainersDTO> updateTrainers(@PathVariable Long id, @RequestBody TrainersDTO trainersDTO) {
        if (trainersService.getTrainersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        trainersDTO.setId(id);
        Trainers trainers = convertToEntity(trainersDTO);
        Trainers updatedTrainers = trainersService.updateTrainers(trainers);
        return ResponseEntity.ok(convertToDTO(updatedTrainers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainers(@PathVariable Long id) {
        if (trainersService.getTrainersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        trainersService.deleteTrainers(id);
        return ResponseEntity.noContent().build();
    }

    private TrainersDTO convertToDTO(Trainers trainers) {
        return new TrainersDTO(
                trainers.getId(),
                trainers.getName(),
                trainers.getSurname(),
                trainers.getLastname(),
                trainers.getSpecialization(),
                trainers.getBirthday(),
                trainers.getEmail(),
                trainers.getPhone(),
                trainers.getPassword(),
                trainers.getEmployment_date()
        );
    }

    private Trainers convertToEntity(TrainersDTO trainersDTO) {
        Trainers trainers = new Trainers();
        trainers.setId(trainersDTO.getId());
        trainers.setName(trainersDTO.getName());
        trainers.setSurname(trainersDTO.getSurname());
        trainers.setLastname(trainersDTO.getLastname());
        trainers.setSpecialization(trainersDTO.getSpecialization());
        trainers.setBirthday(trainersDTO.getBirthday());
        trainers.setEmail(trainersDTO.getEmail());
        trainers.setPhone(trainersDTO.getPhone());
        trainers.setPassword(trainersDTO.getPassword());
        trainers.setEmployment_date(trainersDTO.getEmployment_date());

        return trainers;
    }
}

