package com.example.course_java.repository;

import com.example.course_java.domain.Gyms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymsRepository extends JpaRepository<Gyms, Long> {
    List<Gyms> findAll();
    Optional<Gyms> findById(Long gymsId);
    Gyms save(Gyms gyms);
    void deleteById(Long gymsId);
}
