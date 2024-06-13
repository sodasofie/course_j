package com.example.course_java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.course_java.domain.Schedule;
import com.example.course_java.repository.ScheduleRepository;
import com.example.course_java.service.ScheduleService;
import org.junit.Before;
import org.junit.Test;

public class ScheduleServiceTest {

    private ScheduleService scheduleService;
    private ScheduleRepository scheduleRepositoryMock;

    @Before
    public void setUp() {
        scheduleRepositoryMock = mock(ScheduleRepository.class);
        scheduleService = new ScheduleService(scheduleRepositoryMock);
    }

    @Test
    public void testGetAllSchedules() {
        List<Schedule> expectedSchedules = new ArrayList<>();
        expectedSchedules.add(new Schedule(1L, LocalDateTime.of(2024, 6, 1, 11, 0),LocalDateTime.of(2024, 6, 1, 12, 30)));
        expectedSchedules.add(new Schedule(2L, LocalDateTime.of(2024, 6, 2, 12, 40),LocalDateTime.of(2024, 6, 2, 14, 0)));
        when(scheduleRepositoryMock.findAll()).thenReturn(expectedSchedules);

        List<Schedule> actualSchedules = scheduleService.getAllSchedule();

        assertEquals(expectedSchedules.size(), actualSchedules.size());
        for (int i = 0; i < expectedSchedules.size(); i++) {
            assertEquals(expectedSchedules.get(i), actualSchedules.get(i));
        }
    }

    @Test
    public void testGetScheduleById() {
        Schedule expectedSchedule = new Schedule(1L, LocalDateTime.of(2024, 6, 1, 11, 0), LocalDateTime.of(2024, 6, 1, 12, 30));
        when(scheduleRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedSchedule));

        Optional<Schedule> actualSchedule = scheduleService.getScheduleById(1L);

        assertTrue(actualSchedule.isPresent());
        assertEquals(expectedSchedule, actualSchedule.get());
    }

    @Test
    public void testCreateSchedule() {
        Schedule newSchedule = new Schedule(null, LocalDateTime.of(2024, 6, 3, 14, 0), LocalDateTime.of(2024, 6, 3, 15, 0));
        Schedule savedSchedule = new Schedule(1L, LocalDateTime.of(2024, 6, 3, 14, 0), LocalDateTime.of(2024, 6, 3, 15, 0));
        when(scheduleRepositoryMock.save(newSchedule)).thenReturn(savedSchedule);

        Schedule createdSchedule = scheduleService.createSchedule(newSchedule);

        assertEquals(savedSchedule, createdSchedule);
    }

    @Test
    public void testUpdateSchedule() {
        Schedule existingSchedule = new Schedule(1L, LocalDateTime.of(2024, 6, 3, 14, 0), LocalDateTime.of(2024, 6, 3, 15, 0));
        Schedule updatedSchedule = new Schedule(1L, LocalDateTime.of(2024, 6, 3, 16, 30), LocalDateTime.of(2024, 6, 3, 17, 30));
        when(scheduleRepositoryMock.save(existingSchedule)).thenReturn(updatedSchedule);

        Schedule result = scheduleService.updateSchedule(existingSchedule);

        assertEquals(updatedSchedule, result);
    }

    @Test
    public void testDeleteSchedule() {
        Long scheduleId = 1L;
        doNothing().when(scheduleRepositoryMock).deleteById(scheduleId);

        scheduleService.deleteSchedule(scheduleId);

        verify(scheduleRepositoryMock, times(1)).deleteById(scheduleId);
    }
}
