package com.example.course_java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.course_java.domain.Users;
import com.example.course_java.repository.UsersRepository;
import com.example.course_java.service.UsersService;
import org.junit.Before;
import org.junit.Test;

public class UsersServiceTest {

    private UsersService usersService;
    private UsersRepository usersRepositoryMock;

    @Before
    public void setUp() {
        usersRepositoryMock = mock(UsersRepository.class);
        usersService = new UsersService(usersRepositoryMock);
    }

    @Test
    public void testGetAllUsers() {

        List<Users> expectedUsers = new ArrayList<>();
        expectedUsers.add(new Users(1L, "Катя", "Жовта", "kate@gmail.com"));
        expectedUsers.add(new Users(2L, "Олег", "Синій", "oleg@ukr.net"));
        when(usersRepositoryMock.findAll()).thenReturn(expectedUsers);

        List<Users> actualUsers = usersService.getAllUsers();

        assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), actualUsers.get(i));
        }
    }

    @Test
    public void testGetUsersById() {
        Users expectedUser = new Users(1L, "John", "Doe", "john@example.com");
        when(usersRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedUser));

        Optional<Users> actualUser = usersService.getUsersById(1L);

        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    public void testCreateUsers() {

        Users newUser = new Users(null, "Alice", "Smith", "alice@example.com");
        Users savedUser = new Users(1L, "Alice", "Smith", "alice@example.com");
        when(usersRepositoryMock.save(newUser)).thenReturn(savedUser);

        Users createdUser = usersService.createUsers(newUser);

        assertEquals(savedUser, createdUser);
    }

    @Test
    public void testUpdateUsers() {

        Users existingUser = new Users(1L, "Alice", "Smith", "Smith", LocalDate.of(1993, 3, 3), "alice@example.com", "111222333", "password", LocalDate.now());
        Users updatedUser = new Users(1L, "Alice", "Johnson", "Smith", LocalDate.of(1993, 3, 3), "alice@example.com", "111222333", "password", LocalDate.now());
        when(usersRepositoryMock.save(existingUser)).thenReturn(updatedUser);

        Users result = usersService.updateUsers(existingUser);

        assertEquals(updatedUser, result);
    }

    @Test
    public void testDeleteUsers() {

        Long userId = 1L;
        doNothing().when(usersRepositoryMock).deleteById(userId);

        usersService.deleteUsers(userId);

        verify(usersRepositoryMock, times(1)).deleteById(userId);
    }
}


