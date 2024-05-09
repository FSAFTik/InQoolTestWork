package com.example.TennisReservation.Services.Surfaces;


import com.example.TennisReservation.Dao.SurfaceDao;
import com.example.TennisReservation.Entities.Surface;
import com.example.TennisReservation.Services.Courts.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SurfaceServiceImpl implements SurfaceService{
    private final SurfaceDao surfaceDao;
    private final CourtService courtService;

    @Override
    public Surface createSurface(Surface surface) {
        return surfaceDao.save(surface);
    }

    @Override
    public Optional<Surface> getSurface(long id) {
        return surfaceDao.findById(id);
    }

    @Override
    public List<Surface> getAllSurfaces() {
        return surfaceDao.findAll();
    }

    @Override
    public Surface updateSurface(long id, Surface surface) {
        Surface existingSurface = getSurface(id).orElseThrow(() -> new RuntimeException("Surface not found"));
        existingSurface.setPricePerHour(surface.getPricePerHour());
        existingSurface.setType(surface.getType());
        surfaceDao.update(existingSurface);
        return surface;
    }

    @Override
    public void deleteSurface(long id) {
        getSurface(id).ifPresent(surfaceDao::delete);
    }

    public boolean canDeleteSurface(long id) {
        Surface surface = getSurface(id).orElseThrow(() -> new RuntimeException("Surface not found"));
        return courtService.getAllCourts().stream().noneMatch(court -> court.getSurface().equals(surface));
    }
}
