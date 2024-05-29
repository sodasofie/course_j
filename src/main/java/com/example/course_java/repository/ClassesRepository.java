package com.example.course_java.repository;

import com.example.course_java.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
}
