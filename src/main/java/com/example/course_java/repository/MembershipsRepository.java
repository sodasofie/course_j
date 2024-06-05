package com.example.course_java.repository;

import com.example.course_java.domain.Memberships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipsRepository extends JpaRepository<Memberships, Long> {
    List<Memberships> findAll();
    Optional<Memberships> findById(Long membershipsId);
    Memberships save(Memberships memberships);
    void deleteById(Long membershipsId);
}
