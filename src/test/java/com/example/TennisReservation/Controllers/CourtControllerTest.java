package com.example.TennisReservation.Controllers;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Services.Courts.CourtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CourtControllerTest {

    @Mock
    private CourtService courtService;

    @InjectMocks
    private CourtController courtController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addCourt_ValidCourt_ReturnsCourt() {
        Court court = new Court();
        when(courtService.CreateCourt(any(Court.class))).thenReturn(court);

        ResponseEntity<Court> response = courtController.addCourt(court);

        assertEquals(ResponseEntity.ok(court), response);
    }

    @Test
    public void getAllCourts_CourtsExist_ReturnsCourtList() {
        List<Court> courts = Arrays.asList(new Court(), new Court());
        when(courtService.getAllCourts()).thenReturn(courts);

        ResponseEntity<List<Court>> response = courtController.getAllCourts();

        assertEquals(ResponseEntity.ok(courts), response);
    }

    @Test
    public void getCourt_ValidId_ReturnsCourt() {
        Court court = new Court();
        when(courtService.getCourt(anyLong())).thenReturn(Optional.of(court));

        ResponseEntity<Court> response = courtController.getCourt(1L);

        assertEquals(ResponseEntity.ok(court), response);
    }

    @Test
    public void getCourt_InvalidId_ReturnsNotFound() {
        when(courtService.getCourt(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Court> response = courtController.getCourt(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void updateCourt_ValidId_ReturnsUpdatedCourt() {
        Court court = new Court();
        when(courtService.UpdateCourt(anyLong(), any(Court.class))).thenReturn(court);

        ResponseEntity<Court> response = courtController.updateCourt(1L, court);

        assertEquals(ResponseEntity.ok(court), response);
    }

    @Test
    public void deleteCourt_CanDelete_ReturnsOk() {
        when(courtService.canDeleteCourt(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = courtController.deleteCourt(1L);

        assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void deleteCourt_CannotDelete_ReturnsBadRequest() {
        when(courtService.canDeleteCourt(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = courtController.deleteCourt(1L);

        assertEquals(ResponseEntity.status(400).build(), response);
    }
}