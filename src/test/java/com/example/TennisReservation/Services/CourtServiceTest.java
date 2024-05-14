package com.example.TennisReservation.Services;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Services.Courts.CourtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourtServiceTest {

    @Mock
    private CourtService courtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCourt_ShouldReturnCreatedCourt() {
        Court court = new Court();
        when(courtService.CreateCourt(court)).thenReturn(court);

        Court createdCourt = courtService.CreateCourt(court);

        assertEquals(court, createdCourt);
    }

    @Test
    public void getCourt_ShouldReturnCourtIfExists() {
        Court court = new Court();
        when(courtService.getCourt(1L)).thenReturn(Optional.of(court));

        Optional<Court> retrievedCourt = courtService.getCourt(1L);

        assertTrue(retrievedCourt.isPresent());
        assertEquals(court, retrievedCourt.get());
    }

    @Test
    public void getCourt_ShouldReturnEmptyIfCourtDoesNotExist() {
        when(courtService.getCourt(1L)).thenReturn(Optional.empty());

        Optional<Court> retrievedCourt = courtService.getCourt(1L);

        assertFalse(retrievedCourt.isPresent());
    }

    @Test
    public void getAllCourts_ShouldReturnAllCourts() {
        Court court1 = new Court();
        Court court2 = new Court();
        List<Court> courts = Arrays.asList(court1, court2);
        when(courtService.getAllCourts()).thenReturn(courts);

        List<Court> retrievedCourts = courtService.getAllCourts();

        assertEquals(2, retrievedCourts.size());
        assertTrue(retrievedCourts.containsAll(courts));
    }

    @Test
    public void updateCourt_ShouldReturnUpdatedCourt() {
        Court court = new Court();
        when(courtService.UpdateCourt(1L, court)).thenReturn(court);

        Court updatedCourt = courtService.UpdateCourt(1L, court);

        assertEquals(court, updatedCourt);
    }

    @Test
    public void canDeleteCourt_ShouldReturnTrueIfCourtCanBeDeleted() {
        when(courtService.canDeleteCourt(1L)).thenReturn(true);

        boolean canDelete = courtService.canDeleteCourt(1L);

        assertTrue(canDelete);
    }

    @Test
    public void canDeleteCourt_ShouldReturnFalseIfCourtCannotBeDeleted() {
        when(courtService.canDeleteCourt(1L)).thenReturn(false);

        boolean canDelete = courtService.canDeleteCourt(1L);

        assertFalse(canDelete);
    }
}
