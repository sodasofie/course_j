package com.example.course_java.repository;

import com.example.course_java.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAll();
    Optional<Schedule> findById(Long scheduleId);
    Schedule save(Schedule schedule);
    void deleteById(Long scheduleId);
}
