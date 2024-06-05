package com.example.course_java.repository;

import com.example.course_java.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    List<Classes> findAll();
    Optional<Classes> findById(Long classesId);
    Classes save(Classes classes);
    void deleteById(Long classesId);
}
