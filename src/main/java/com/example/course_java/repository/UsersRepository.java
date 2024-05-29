package com.example.course_java.repository;

import com.example.course_java.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // додаткові методи для специфічних операцій з базою даних, якщо потрібно
}
