package com.example.course_java.repository;

import com.example.course_java.domain.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainersRepository extends JpaRepository<Trainers, Long> {
    List<Trainers> findAll();
    Optional<Trainers> findById(Long trainersId);
    Trainers save(Trainers trainers);
    void deleteById(Long trainersId);
}

