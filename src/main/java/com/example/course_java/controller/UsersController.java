package com.example.course_java.controller;

import com.example.course_java.domain.Users;
import com.example.course_java.repository.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Додайте методи для інших операцій CRUD (створення, оновлення, видалення)
}
