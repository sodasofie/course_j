package com.example.course_java.repository;

import com.example.course_java.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//для взаємодії з бд, save(), findById(), delete()
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
    Optional<Users> findById(Long usersId);
    Users save(Users users);
    void deleteById(Long usersId);
}
