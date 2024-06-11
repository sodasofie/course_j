package com.example.course_java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.course_java.domain.Classes;
import com.example.course_java.repository.ClassesRepository;
import com.example.course_java.service.ClassesService;
import org.junit.Before;
import org.junit.Test;

public class ClassesServiceTest {

    private ClassesService classesService;
    private ClassesRepository classesRepositoryMock;

    @Before
    public void setUp() {
        classesRepositoryMock = mock(ClassesRepository.class);
        classesService = new ClassesService(classesRepositoryMock);
    }

    @Test
    public void testGetAllClasses() {
        List<Classes> expectedClasses = new ArrayList<>();
        expectedClasses.add(new Classes(1L, "Йога", "Клас йоги для новачків"));
        expectedClasses.add(new Classes(2L, "Фітнес", "Фітнес 14-18 років"));
        when(classesRepositoryMock.findAll()).thenReturn(expectedClasses);

        List<Classes> actualClasses = classesService.getAllClasses();

        assertEquals(expectedClasses.size(), actualClasses.size());
        for (int i = 0; i < expectedClasses.size(); i++) {
            assertEquals(expectedClasses.get(i), actualClasses.get(i));
        }
    }

    @Test
    public void testGetClassById() {
        Classes expectedClass = new Classes(1L, "Йога", "Клас йоги для новачків");
        when(classesRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedClass));

        Optional<Classes> actualClass = classesService.getClassesById(1L);

        assertTrue(actualClass.isPresent());
        assertEquals(expectedClass, actualClass.get());
    }

    @Test
    public void testCreateClass() {
        Classes newClass = new Classes(null, "Пілатес", "Пілатес середнього рівня складності");
        Classes savedClass = new Classes(1L, "Пілатес", "Пілатес середнього рівня складності");
        when(classesRepositoryMock.save(newClass)).thenReturn(savedClass);

        Classes createdClass = classesService.createClasses(newClass);

        assertEquals(savedClass, createdClass);
    }

    @Test
    public void testUpdateClass() {
        Classes existingClass = new Classes(1L, "Пілатес", "Пілатес середнього рівня складності");
        Classes updatedClass = new Classes(1L, "Пілатес Для Новачків", "Базовий пілатес всіх рівнів, для новачків");
        when(classesRepositoryMock.save(existingClass)).thenReturn(updatedClass);

        Classes result = classesService.updateClasses(existingClass);

        assertEquals(updatedClass, result);
    }

    @Test
    public void testDeleteClass() {
        Long classId = 1L;
        doNothing().when(classesRepositoryMock).deleteById(classId);

        classesService.deleteClasses(classId);

        verify(classesRepositoryMock, times(1)).deleteById(classId);
    }
}
