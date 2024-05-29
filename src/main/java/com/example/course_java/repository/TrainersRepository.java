package com.example.course_java.repository;

import com.example.course_java.domain.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TrainersRepository extends JpaRepository<Trainers, Long> {
}
