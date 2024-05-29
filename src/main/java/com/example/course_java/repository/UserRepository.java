package com.example.course_java.repository;

import com.example.course_java.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // Додайте додаткові методи для специфічних операцій з базою даних, якщо потрібно
}
