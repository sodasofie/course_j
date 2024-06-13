package com.example.course_java.controller;

import com.example.course_java.domain.Users;
import com.example.course_java.dto.UsersDTO;
import com.example.course_java.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<UsersDTO> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUsersById(@PathVariable Long id) {
        Optional<Users> users = usersService.getUsersById(id);
        return users.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsersDTO createUsers(@RequestBody UsersDTO usersDTO) {
        Users users = convertToEntity(usersDTO);
        Users createdUsers = usersService.createUsers(users);
        return convertToDTO(createdUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUsers(@PathVariable Long id, @RequestBody UsersDTO usersDTO) {
        if (usersService.getUsersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usersDTO.setId(id);
        Users users = convertToEntity(usersDTO);
        Users updatedUsers = usersService.updateUsers(users);
        return ResponseEntity.ok(convertToDTO(updatedUsers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        if (usersService.getUsersById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usersService.deleteUsers(id);
        return ResponseEntity.noContent().build();
    }

    private UsersDTO convertToDTO(Users users) {
        return new UsersDTO(
                users.getId(),
                users.getName(),
                users.getSurname(),
                users.getLastname(),
                users.getBirthday(),
                users.getEmail(),
                users.getPhone(),
                users.getPassword(),
                users.getRegister_date()
        );
    }

    private Users convertToEntity(UsersDTO usersDTO) {
        Users users = new Users();
        users.setId(usersDTO.getId());
        users.setName(usersDTO.getName());
        users.setSurname(usersDTO.getSurname());
        users.setLastname(usersDTO.getLastname());
        users.setBirthday(usersDTO.getBirthday());
        users.setEmail(usersDTO.getEmail());
        users.setPhone(usersDTO.getPhone());
        users.setPassword(usersDTO.getPassword());
        users.setRegister_date(usersDTO.getRegister_date());

        return users;
    }
}
