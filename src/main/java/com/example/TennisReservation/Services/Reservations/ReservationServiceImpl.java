package com.example.TennisReservation.Services.Reservations;

import com.example.TennisReservation.Dao.CustomerDao;
import com.example.TennisReservation.Dao.ReservationDao;
import com.example.TennisReservation.Entities.Court;
import com.example.TennisReservation.Entities.Customer;
import com.example.TennisReservation.Entities.Reservation;
import com.example.TennisReservation.Services.Courts.CourtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final CustomerDao customerDao;
    private final CourtServiceImpl courtService;

    @Override
    public Reservation createReservation(Reservation reservation) {
        reservation.setCourt(courtService.getCourt(reservation.getCourtId()).
                orElseThrow(() -> new IllegalArgumentException("The reservation court does not exist")));

        if (isInConflict(reservation)) {
            throw new IllegalArgumentException("The reservation overlaps with an existing reservation or is in the past.");
        }

        reservation.setCustomer(customerDao.findByPhoneNumber(
                reservation.getCustomerPhoneNumber()).orElseGet(() ->
                createNewCustomer(reservation.getCustomerPhoneNumber(), reservation.getCustomerName())));

        return reservationDao.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations(String phoneNumber) {
        return reservationDao.getAllReservations(phoneNumber);
    }

    @Override
    public List<Reservation> getAllReservations(long courtId) {
        Optional<Court> court = courtService.getCourt(courtId);
        return court.isPresent() ? reservationDao.getAllReservations(court.get()) : List.of();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDao.findAll();
    }

    @Override
    public List<Reservation> getAllFutureReservations(long courtId) {
        return getAllReservations(courtId).stream()
                .filter(reservation -> reservation.getStartTime().isAfter(LocalDateTime.now()))
                .toList();
    }

    @Override
    public Optional<Reservation> getReservation(long id) {
        return reservationDao.findById(id);
    }

    @Override
    public Reservation updateReservation(long id, Reservation reservation) {
        Reservation existingReservation = getReservation(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with id " + id + " does not exist"));

        existingReservation.setCourt(courtService.getCourt(reservation.getCourtId())
                .orElseThrow(() -> new IllegalArgumentException("The reservation court does not exist")));

        existingReservation.setStartTime(reservation.getStartTime());
        existingReservation.setEndTime(reservation.getEndTime());
        existingReservation.setDuo(reservation.isDuo());

        if (isInConflict(existingReservation)) {
            throw new IllegalArgumentException("The reservation overlaps with an existing reservation or is in the past.");
        }
        existingReservation.setCustomer(customerDao.findByPhoneNumber(
                reservation.getCustomerPhoneNumber()).orElseGet(() ->
                createNewCustomer(reservation.getCustomerPhoneNumber(), reservation.getCustomerName())));
        reservationDao.update(existingReservation);
        return existingReservation;
    }

    @Override
    public void deleteReservation(long id) {
        getReservation(id).ifPresent(reservationDao::delete);
    }

    private boolean isInConflict(Reservation reservation) {
        if (reservation.getStartTime().isBefore(LocalDateTime.now()) || reservation.getEndTime().isBefore(reservation.getStartTime())) {
            return true;
        }
        return getAllReservations(reservation.getCourt().getId()).stream().anyMatch(existing ->
                !Objects.equals(existing.getId(), reservation.getId()) &&
                        !existing.getEndTime().isBefore(reservation.getStartTime()) &&
                        !existing.getStartTime().isAfter(reservation.getEndTime()));
    }

    private Customer createNewCustomer(String phoneNumber, String name) {
        Customer customer = new Customer();
        customer.setPhoneNumber(phoneNumber);
        customer.setName(name);
        customerDao.save(customer);
        return customer;
    }
}
