package com.example.TennisReservation.Configurators;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Role;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Courts.CourtService;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import com.example.TennisReservation.Services.Users.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CourtInitializer {
    private final CourtService courtService;
    private final SurfaceService surfaceService;
    private final UserServiceImpl userService;

    @Value("${app.court-initializer}")
    private boolean initializeCourts;

    @PostConstruct
    @Transactional
    public void initData() {
        if (initializeCourts) {
            Surface[] surfaces = {
                    createAndSaveSurface("Clay", 5000),
                    createAndSaveSurface("Hard", 10000)
            };

            for (int i = 0; i < 2; i ++){
                for (Surface surface : surfaceService.getAllSurfaces()) {
                    Court court = new Court();
                    court.setSurface(surface);
                    court.setSurfaceId(surface.getId());
                    courtService.CreateCourt(court);
                }
            }
        }
        userService.createUser("admin", "admin", Collections.singleton(Role.ADMIN));
    }

    private Surface createAndSaveSurface(String type, int pricePerHour) {
        Surface surface = new Surface();
        surface.setType(type);
        surface.setPricePerHour(pricePerHour);
        return surfaceService.createSurface(surface);
    }
}