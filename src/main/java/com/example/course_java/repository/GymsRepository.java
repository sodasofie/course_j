package com.example.course_java.repository;

import com.example.course_java.domain.Gyms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GymsRepository extends JpaRepository<Gyms, Long> {
}
