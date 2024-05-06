package com.example.TennisReservation.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * Entity representing a reservation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false, name = "end_time")
    private String endTime;

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @Column(nullable = false, name = "is_duo_game")
    private boolean isDuo;
}
