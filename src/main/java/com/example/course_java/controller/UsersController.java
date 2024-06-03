package com.example.course_java.controller;

import com.example.course_java.domain.Users;
import com.example.course_java.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//обробка HTTP-запитів, приймають їх, оброб та поверт відпов @GetMapping @PutMapping @PostMapping
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

    @GetMapping("/{id}")
    public Users getUsersById(@PathVariable Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Users createUsers(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @PutMapping("/{id}")
    public Users updateUsers(@PathVariable Long id, @RequestBody Users users) {
        users.setId(id);
        return usersRepository.save(users);
    }

    @DeleteMapping("/{id}")
    public void deleteUsers(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }
}
