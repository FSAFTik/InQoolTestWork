package com.example.TennisReservation.Services.Courts;

import com.example.TennisReservation.Dao.CourtDao;
import com.example.TennisReservation.Dao.ReservationDao;
import com.example.TennisReservation.Dao.SurfaceDao;
import com.example.TennisReservation.Entities.Court;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourtServiceImpl implements CourtService{
    private final CourtDao courtDao;
    private final ReservationDao reservationDao;
    private final SurfaceDao surfaceDao;

    @Override
    public Court CreateCourt(Court court) {
        court.setSurface(surfaceDao.findById(court.getSurfaceId())
                .orElseThrow(() -> new IllegalArgumentException("The surface does not exist")));
        return courtDao.save(court);
    }

    @Override
    public Optional<Court> getCourt(long id) {
            return courtDao.findById(id);
    }

    @Override
    public List<Court> getAllCourts() {
        return courtDao.findAll();
    }

    @Override
    public Court UpdateCourt(long id, Court court) {
        Court existingCourt = getCourt(id).orElseThrow(() -> new RuntimeException("Court not found"));
        existingCourt.setSurface(surfaceDao.findById(court.getSurfaceId())
                .orElseThrow(() -> new IllegalArgumentException("The surface does not exist")));
        existingCourt.setSurfaceId(court.getSurfaceId());
        courtDao.update(existingCourt);
        return existingCourt;
    }

    @Override
    public void deleteCourt(long id) {
        getCourt(id).ifPresent(courtDao::delete);
    }

    public boolean canDeleteCourt(long id) {
        Court court = getCourt(id).orElseThrow(() -> new RuntimeException("Court not found"));
        return reservationDao.getAllReservations(court).stream()
                .noneMatch(reservation -> reservation.getStartTime().isAfter(LocalDateTime.now()));
    }
}
