package com.example.TennisReservation;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Courts.CourtService;
import com.example.TennisReservation.Services.Courts.CourtServiceImpl;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CourtInitializer {
    private final CourtService courtService;
    private final SurfaceService surfaceService;

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
    }

    private Surface createAndSaveSurface(String type, int pricePerHour) {
        Surface surface = new Surface();
        surface.setType(type);
        surface.setPricePerHour(pricePerHour);
        return surfaceService.createSurface(surface);
    }
}