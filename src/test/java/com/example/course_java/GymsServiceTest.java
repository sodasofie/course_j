package com.example.course_java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.course_java.domain.Gyms;
import com.example.course_java.repository.GymsRepository;
import com.example.course_java.service.GymsService;
import org.junit.Before;
import org.junit.Test;

public class GymsServiceTest {

    private GymsService gymsService;
    private GymsRepository gymsRepositoryMock;

    @Before
    public void setUp() {
        gymsRepositoryMock = mock(GymsRepository.class);
        gymsService = new GymsService(gymsRepositoryMock);
    }

    @Test
    public void testGetAllGyms() {
        List<Gyms> expectedGyms = new ArrayList<>();
        expectedGyms.add(new Gyms(1L, "Північний", "вул. Північна 1"));
        expectedGyms.add(new Gyms(2L, "Східний", "просп. Східна 2/Б"));
        when(gymsRepositoryMock.findAll()).thenReturn(expectedGyms);

        List<Gyms> actualGyms = gymsService.getAllGyms();

        assertEquals(expectedGyms.size(), actualGyms.size());
        for (int i = 0; i < expectedGyms.size(); i++) {
            assertEquals(expectedGyms.get(i), actualGyms.get(i));
        }
    }

    @Test
    public void testGetGymById() {
        Gyms expectedGym = new Gyms(1L, "Північний", "вул.Північна 1");
        when(gymsRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedGym));

        Optional<Gyms> actualGym = gymsService.getGymsById(1L);

        assertTrue(actualGym.isPresent());
        assertEquals(expectedGym, actualGym.get());
    }

    @Test
    public void testCreateGym() {
        Gyms newGym = new Gyms(null, "Південий", "бул. Південна 3/А");
        Gyms savedGym = new Gyms(1L, "Південий", "бул. Південна 3/А");
        when(gymsRepositoryMock.save(newGym)).thenReturn(savedGym);

        Gyms createdGym = gymsService.createGyms(newGym);

        assertEquals(savedGym, createdGym);
    }

    @Test
    public void testUpdateGym() {
        Gyms existingGym = new Gyms(1L, "Південий", "бул. Південна 3/А");
        Gyms updatedGym = new Gyms(1L, "Південий", "бул. Південна 13/В");
        when(gymsRepositoryMock.save(existingGym)).thenReturn(updatedGym);

        Gyms result = gymsService.updateGyms(existingGym);

        assertEquals(updatedGym, result);
    }

    @Test
    public void testDeleteGym() {
        Long gymId = 1L;
        doNothing().when(gymsRepositoryMock).deleteById(gymId);

        gymsService.deleteGyms(gymId);

        verify(gymsRepositoryMock, times(1)).deleteById(gymId);
    }
}
