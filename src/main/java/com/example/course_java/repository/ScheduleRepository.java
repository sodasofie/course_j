package com.example.course_java.repository;

import com.example.course_java.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
