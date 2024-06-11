package com.example.course_java.service;
import com.example.course_java.domain.Users;
import com.example.course_java.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUsersById(Long usersId) {
        return usersRepository.findById(usersId);
    }

    public Users createUsers(Users users) {
        return usersRepository.save(users);
    }

    public Users updateUsers(Users users) {
        return usersRepository.save(users);
    }

    public void deleteUsers(Long usersId) {
        usersRepository.deleteById(usersId);
    }
}

