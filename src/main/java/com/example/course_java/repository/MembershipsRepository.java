package com.example.course_java.repository;

import com.example.course_java.domain.Memberships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MembershipsRepository extends JpaRepository<Memberships, Long> {
}
