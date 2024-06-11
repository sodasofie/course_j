package com.example.course_java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.course_java.domain.Trainers;
import com.example.course_java.repository.TrainersRepository;
import com.example.course_java.service.TrainersService;
import org.junit.Before;
import org.junit.Test;

public class TrainersServiceTest {

    private TrainersService trainersService;
    private TrainersRepository trainersRepositoryMock;

    @Before
    public void setUp() {
        trainersRepositoryMock = mock(TrainersRepository.class);
        trainersService = new TrainersService(trainersRepositoryMock);
    }

    @Test
    public void testGetAllTrainers() {
        List<Trainers> expectedTrainers = new ArrayList<>();
        expectedTrainers.add(new Trainers(1L, "Василь", "Іванович", "Тренер залу"));
        expectedTrainers.add(new Trainers(2L, "Світлана", "Михайлівна", "Тренер фітнесу"));
        when(trainersRepositoryMock.findAll()).thenReturn(expectedTrainers);

        List<Trainers> actualTrainers = trainersService.getAllTrainers();

        assertEquals(expectedTrainers.size(), actualTrainers.size());
        for (int i = 0; i < expectedTrainers.size(); i++) {
            assertEquals(expectedTrainers.get(i), actualTrainers.get(i));
        }
    }

    @Test
    public void testGetTrainerById() {
        Trainers expectedTrainer = new Trainers(1L, "Василь", "Іванович", "Тренер залу");
        when(trainersRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedTrainer));

        Optional<Trainers> actualTrainer = trainersService.getTrainersById(1L);

        assertTrue(actualTrainer.isPresent());
        assertEquals(expectedTrainer, actualTrainer.get());
    }

    @Test
    public void testCreateTrainer() {
        Trainers newTrainer = new Trainers(null, "Ольга", "Олександрівна", "Тренер йоги");
        Trainers savedTrainer = new Trainers(1L, "Ольга", "Олександрівна", "Тренер йоги");
        when(trainersRepositoryMock.save(newTrainer)).thenReturn(savedTrainer);

        Trainers createdTrainer = trainersService.createTrainers(newTrainer);

        assertEquals(savedTrainer, createdTrainer);
    }

    @Test
    public void testUpdateTrainer() {
        Trainers existingTrainer = new Trainers(1L, "Василь", "Іванович", "Тренер залу");
        Trainers updatedTrainer = new Trainers(1L, "Максим", "Іванович", "Тренер залу");
        when(trainersRepositoryMock.save(existingTrainer)).thenReturn(updatedTrainer);

        Trainers result = trainersService.updateTrainers(existingTrainer);

        assertEquals(updatedTrainer, result);
    }

    @Test
    public void testDeleteTrainer() {
        Long trainerId = 1L;
        doNothing().when(trainersRepositoryMock).deleteById(trainerId);

        trainersService.deleteTrainers(trainerId);

        verify(trainersRepositoryMock, times(1)).deleteById(trainerId);
    }
}
