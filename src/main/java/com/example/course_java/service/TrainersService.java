package com.example.course_java.service;
import com.example.course_java.domain.Trainers;
import com.example.course_java.repository.TrainersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainersService {
    private final TrainersRepository trainersRepository;
    @Autowired
    public TrainersService(TrainersRepository trainersRepository) {
        this.trainersRepository = trainersRepository;
    }

    public List<Trainers> getAllTrainers() {
        return trainersRepository.findAll();
    }

    public Optional<Trainers> getTrainersById(Long trainersId) {
        return trainersRepository.findById(trainersId);
    }

    public Trainers createTrainers(Trainers trainers) {
        return trainersRepository.save(trainers);
    }

    public Trainers updateTrainers(Trainers trainers) {
        return trainersRepository.save(trainers);
    }

    public void deleteTrainers(Long trainersId) {
        trainersRepository.deleteById(trainersId);
    }
}

