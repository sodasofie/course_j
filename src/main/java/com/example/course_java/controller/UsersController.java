package com.example.course_java.controller;
import com.example.course_java.domain.Users;
import com.example.course_java.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//обробка HTTP-запитів, приймають їх, оброб та поверт відпов @GetMapping @PutMapping @PostMapping
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable Long id) {
        Optional<Users> user = usersService.getUsersById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Users createUsers(@RequestBody Users users) {
        return usersService.createUsers(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable Long id, @RequestBody Users users) {
        if (usersService.getUsersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        users.setId(id); // Ensure the id is set
        return ResponseEntity.ok(usersService.updateUsers(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        if (usersService.getUsersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usersService.deleteUsers(id);
        return ResponseEntity.noContent().build();
    }
}
