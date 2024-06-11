package com.example.course_java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.course_java.domain.Memberships;
import com.example.course_java.repository.MembershipsRepository;
import com.example.course_java.service.MembershipsService;
import org.junit.Before;
import org.junit.Test;

public class MembershipsServiceTest {

    private MembershipsService membershipsService;
    private MembershipsRepository membershipsRepositoryMock;

    @Before
    public void setUp() {
        membershipsRepositoryMock = mock(MembershipsRepository.class);
        membershipsService = new MembershipsService(membershipsRepositoryMock);
    }

    // Допоміжний метод для створення BigDecimal з double
    private BigDecimal bd(double value) {
        return BigDecimal.valueOf(value);
    }

    @Test
    public void testGetAllMemberships() {
        List<Memberships> expectedMemberships = new ArrayList<>();
        expectedMemberships.add(new Memberships(1L, "Базовий", "Базовий абонемент на зал 18 годин/місяць", bd(250.0)));
        expectedMemberships.add(new Memberships(2L, "Фітнес молодь", "Абонемент на фітнес для людей віком 14-18 років", bd(200.0)));
        when(membershipsRepositoryMock.findAll()).thenReturn(expectedMemberships);

        List<Memberships> actualMemberships = membershipsService.getAllMemberships();

        assertEquals(expectedMemberships.size(), actualMemberships.size());
        for (int i = 0; i < expectedMemberships.size(); i++) {
            assertEquals(expectedMemberships.get(i), actualMemberships.get(i));
        }
    }

    @Test
    public void testGetMembershipById() {
        Memberships expectedMembership = new Memberships(1L, "Базовий", "Базовий абонемент на зал 18 годин/місяць", bd(200.0));
        when(membershipsRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedMembership));

        Optional<Memberships> actualMembership = membershipsService.getMembershipsById(1L);

        assertTrue(actualMembership.isPresent());
        assertEquals(expectedMembership, actualMembership.get());
    }

    @Test
    public void testCreateMembership() {
        Memberships newMembership = new Memberships(null, "Преміум", "Преміум абонемент на зал 36 годин/місяць", bd(320.0));
        Memberships savedMembership = new Memberships(1L, "Преміум", "Преміум абонемент на зал 36 годин/місяць", bd(320.0));
        when(membershipsRepositoryMock.save(newMembership)).thenReturn(savedMembership);

        Memberships createdMembership = membershipsService.createMemberships(newMembership);

        assertEquals(savedMembership, createdMembership);
    }

    @Test
    public void testUpdateMembership() {
        Memberships existingMembership = new Memberships(1L, "Преміум", "Преміум абонемент на зал 36 годин/місяць", bd(320.0));
        Memberships updatedMembership = new Memberships(1L, "Преміум Плюс", "Преміум абонемент на зал 40 годин/місяць з додатковими перевагами", bd(400.0));
        when(membershipsRepositoryMock.save(existingMembership)).thenReturn(updatedMembership);

        Memberships result = membershipsService.updateMemberships(existingMembership);

        assertEquals(updatedMembership, result);
    }

    @Test
    public void testDeleteMembership() {
        Long membershipId = 1L;
        doNothing().when(membershipsRepositoryMock).deleteById(membershipId);

        membershipsService.deleteMemberships(membershipId);

        verify(membershipsRepositoryMock, times(1)).deleteById(membershipId);
    }
}
