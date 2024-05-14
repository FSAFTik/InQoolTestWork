package com.example.TennisReservation.Configurators;

import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Role;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Entities.User;
import com.example.TennisReservation.Services.Courts.CourtService;
import com.example.TennisReservation.Services.Surfaces.SurfaceService;
import com.example.TennisReservation.Services.Users.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Component for initializing data in the application.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final CourtService courtService;
    private final SurfaceService surfaceService;
    private final UserService userService;

    @Value("${app.court-initializer}")
    private boolean initializeCourts;

    /**
     * Method that is executed after dependency injection is done to perform any initialization.
     * Creates users and, if specified in the configuration, initializes courts with different surfaces.
     */
    @PostConstruct
    @Transactional
    public void initData() {
        User admin = User.builder().username("admin").roles(Collections.singleton(Role.ADMIN)).build();
        admin.setPassword("admin");
        userService.createUser(admin);
        if (initializeCourts) {
            createAndSaveSurface("Clay", 5000);
            createAndSaveSurface("Hard", 10000);

            for (int i = 0; i < 2; i ++){
                for (Surface surface : surfaceService.getAllSurfaces()) {
                    Court court = new Court();
                    court.setSurface(surface);
                    court.setSurfaceId(surface.getId());
                    courtService.CreateCourt(court);
                }
            }
        }
        User user = User.builder().username("user").roles(Collections.singleton(Role.USER)).build();
        user.setPassword("user");
        userService.createUser(user);
    }

    /**
     * Creates a new surface and saves it using the surface service.
     * @param type The type of the surface.
     * @param pricePerHour The price per hour of the surface.
     */
    private void createAndSaveSurface(String type, int pricePerHour) {
        Surface surface = new Surface();
        surface.setType(type);
        surface.setPricePerHour(pricePerHour);
        surfaceService.createSurface(surface);
    }
}